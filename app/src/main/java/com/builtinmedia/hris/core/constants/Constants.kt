package com.builtinmedia.hris.core.constants
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
     val ACCESS_TOKEN = stringPreferencesKey("access_token")
     val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

     const val DATASTORE_NAME = "HRIS_PREFRENCES"

     const val MAX_PHOTO_SIZE_BYTES = 2 * 1024 * 1024

     const val DEFAULT_LIMIT = 5

}