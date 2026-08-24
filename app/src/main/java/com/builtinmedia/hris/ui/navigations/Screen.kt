package com.builtinmedia.hris.ui.navigations

sealed class Screen(val route: String) {
    object Login: Screen("login_screen")
    object Home: Screen("home_screen")
    object LeaveRequest: Screen("leave_request_screen")
    object Payslip: Screen("payslip_screen")
    object Profile: Screen("profile_screen")
}