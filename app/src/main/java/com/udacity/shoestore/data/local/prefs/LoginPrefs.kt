package com.udacity.shoestore.data.local.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.udacity.shoestore.utils.common.Constants

class LoginPrefs constructor(context: Context) {
    val loginPrefs = context.getSharedPreferences(Constants.USER_PREF, MODE_PRIVATE)

    companion object {
        const val USER_DB = "USER_DB"
    }

    fun storeUser(userName: String, password: String) {
        Log.i("storeUser", userName + "_" + password)
        loginPrefs.edit().putString(USER_DB, userName + "_" + password).apply()
    }


    fun isUserAuthenticated(userName: String, password: String): Boolean {
        val keys = loginPrefs.all
        keys.forEach {
            Log.i("isUserAuthenticated", it.value.toString());

            var mainData = it.value.toString().split("_")
            var uName = mainData[0]
            var pass = mainData[1]
            Log.i("isUserAuthenticated", uName + " " + pass);
            if (uName.equals(userName) && pass.equals(password)) {
                return true
            }
        }
        return false
    }

}