package com.builtinmedia.hris.ui.navigations

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Login: Screen("login_screen")
    object Presence: Screen("presence_screen")
    object LeaveRequest: Screen("leave_request_screen")
    object Payslip: Screen("payslip_screen")
    object Profile: Screen("profile_screen")
}