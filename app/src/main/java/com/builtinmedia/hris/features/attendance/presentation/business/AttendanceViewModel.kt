package com.builtinmedia.hris.features.attendance.presentation.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import arrow.core.Either
import com.builtinmedia.hris.features.attendance.domain.usecase.CheckInUseCase
import com.builtinmedia.hris.features.attendance.domain.usecase.CheckOutUseCase
import com.builtinmedia.hris.features.attendance.domain.usecase.GetAttendanceHistoryUseCase
import com.builtinmedia.hris.features.attendance.domain.usecase.GetLatestAnnouncementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

sealed interface AttendanceUiEvent {
    data class ShowSnackbar(val message: String) : AttendanceUiEvent
}
@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val getAttendanceHistoryUseCase: GetAttendanceHistoryUseCase,
    private val getLatestAnnouncementUseCase: GetLatestAnnouncementUseCase,
    private val checkInUseCase: CheckInUseCase,
    private val checkOutUseCase: CheckOutUseCase
) : ViewModel() {

    //    state
    private val _state = MutableStateFlow(AttendanceState())
    val state: StateFlow<AttendanceState> = _state.asStateFlow()

    //event
    private val _uiEvent = Channel<AttendanceUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    private val timeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    private var durationTickerRunning = false

    fun onEvent(event: AttendanceEvent){
        when(event) {
            AttendanceEvent.LoadData -> loadData()
            AttendanceEvent.Refresh -> loadData(showLoading = false)
            is AttendanceEvent.CheckIn -> checkIn(event.lat, event.long, event.foto)
            is AttendanceEvent.CheckOut -> checkOut(event.lat, event.long, event.foto)
            AttendanceEvent.ErrorShown -> _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun loadData(showLoading: Boolean = true){
        viewModelScope.launch {
            _state.update { it.copy(isAnnouncementLoading = true) }
            when (val result = getLatestAnnouncementUseCase()) {
                is Either.Right -> _state.update {
                    it.copy(isAnnouncementLoading = false, announcements = result.value)
                }
                is Either.Left -> {
                    _state.update { it.copy(isAnnouncementLoading = false) }
                    _uiEvent.send(AttendanceUiEvent.ShowSnackbar(result.value.message ?: "Gagal memuat pengumuman"))
                }
            }
        }
    }

    private fun checkIn(lat: Double, long: Double, foto: File?=null){
        if(_state.value.isSubmitting) return
        viewModelScope.launch {
            _state.update { it.copy(isSubmitting = true, errorMessage = null) }
            when(val result = checkInUseCase(lat, long, foto)){
                is Either.Right -> {
                    _state.update { it.copy(isSubmitting = false, today = result.value) }
                    startDurationTickerIfNeeded()
                    _uiEvent.send(AttendanceUiEvent.ShowSnackbar("Absen masuk berhasil!!!"))
                }
                is Either.Left -> {
                    _state.update { it.copy(isSubmitting = false) }
                    _uiEvent.send(AttendanceUiEvent.ShowSnackbar(result.value.message ?: "Absen masuk gagal"))
                }
            }
        }
    }

    private fun checkOut(lat: Double, long: Double, foto: File?=null){
        if(_state.value.isSubmitting) return
        viewModelScope.launch {
            _state.update { it.copy(isSubmitting = true, errorMessage = null) }
            when(val result = checkOutUseCase(lat, long, foto)){
                is Either.Right -> {
                    _state.update { it.copy(isSubmitting = false, today = result.value) }
                    _uiEvent.send(AttendanceUiEvent.ShowSnackbar("Absen pulang berhasil!!!"))
                }
                is Either.Left -> {
                    _state.update { it.copy(isSubmitting = false) }
                    _uiEvent.send(AttendanceUiEvent.ShowSnackbar(result.value.message ?: "Absen pulang gagal"))
                }
            }
        }
    }

    private fun startDurationTickerIfNeeded() {
        val today = _state.value.today
        if(today?.isCurrentlyWorking != true || durationTickerRunning) return

        durationTickerRunning = true
        viewModelScope.launch {
            while (_state.value.today?.isCurrentlyWorking == true){
                updateDurationText()
                delay(TimeUnit.MINUTES.toMillis(1))
            }
            durationTickerRunning = false
        }
    }

    private fun updateDurationText(){
        val jamMasuk = _state.value.today?.jamMasuk ?: return
        val checkInTime = runCatching { timeFormatter.parse(jamMasuk) }.getOrNull() ?: return
        val diffMillis = Date().time - checkInTime.time
        val hours = TimeUnit.MILLISECONDS.toHours(diffMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis) % 60
        _state.update { it.copy(workingDurationText = "${hours}h ${minutes}m") }
    }

}