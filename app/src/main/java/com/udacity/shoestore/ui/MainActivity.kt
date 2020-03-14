package com.udacity.shoestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.data.local.prefs.UserPrefrence
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var prefs: UserPrefrence
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = UserPrefrence(this)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (prefs.IS_LOGGED_IN()) {
            navController.navigate(
                R.id.splash, null,
                NavOptions.Builder().setPopUpTo(R.id.login, true).build()
            )
        }

        Timber.plant(Timber.DebugTree())
    }
}
