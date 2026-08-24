package com.builtinmedia.hris.core.constants
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
     val ACCESS_TOKEN = stringPreferencesKey("access_token")
     val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

     const val DATASTORE_NAME = "HRIS_PREFRENCES"


}