package com.udacity.shoestore.data.local.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.udacity.shoestore.utils.common.Constants

class UserPrefrence constructor(context: Context) {
    val appPrefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE)

    companion object {
        const val NAME = "NAME"
        const val USER_NAME = "USER_NAME"
        const val PASSWORD = "USER_PASSWORD"
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
        const val ONBOARDING = "ONBOARDING"
    }

    fun getUserName(): String {
        return appPrefs.getString(USER_NAME, "N/A")!!
    }

    fun setUserName(name: String) {
        appPrefs.edit().putString(USER_NAME, name).apply()
    }

    fun getPassword(): String {
        return appPrefs.getString(PASSWORD, "N/A")!!
    }

    fun setPassword(password: String) {
        appPrefs.edit().putString(PASSWORD, password).apply()
    }

    fun IS_LOGGED_IN(): Boolean {
        return appPrefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun SET_LOGGED_IN(logged: Boolean) {
        appPrefs.edit().putBoolean(IS_LOGGED_IN, logged).apply()
    }

    fun getName(): String {
        return appPrefs.getString(NAME, "N/A")!!
    }

    fun setName(name: String) {
        appPrefs.edit().putString(NAME, name).apply()
    }

    fun setOnBoardingScreenStatus(status: Boolean) {
        appPrefs.edit().putBoolean(ONBOARDING, status).apply()
    }

    fun getOnBoardingScreenStatus(): Boolean {
        return appPrefs.getBoolean(ONBOARDING, false)
    }

}