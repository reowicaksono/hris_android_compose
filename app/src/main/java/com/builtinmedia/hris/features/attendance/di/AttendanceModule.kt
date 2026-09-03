package com.builtinmedia.hris.features.attendance.di

import com.builtinmedia.hris.features.attendance.data.datasource.AttendanceRemoteDataSource
import com.builtinmedia.hris.features.attendance.data.repositories.AttendanceRepositoriesImpl
import com.builtinmedia.hris.features.attendance.domain.repositories.AttendanceRepositories
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AttendanceModule {

    @Binds
    @Singleton
    abstract fun bindAttendanceRepositories(impl: AttendanceRepositoriesImpl): AttendanceRepositories

    companion object{
        @Provides
        @Singleton
        fun provideAttendanceRemoteDataSource(retrofit: Retrofit): AttendanceRemoteDataSource = retrofit.create(
            AttendanceRemoteDataSource::class.java)
    }
}