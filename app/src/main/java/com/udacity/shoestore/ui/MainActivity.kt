package com.udacity.shoestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.data.local.prefs.UserPrefrence
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var prefs: UserPrefrence
    lateinit var navController: NavController
    lateinit var shoeViewModel: ShoeViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = UserPrefrence(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        shoeViewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
        setupActionBarWithNavController(navController, appBarConfiguration)


        if (prefs.IS_LOGGED_IN()) {
            navController.navigate(
                R.id.splash
            )
        }

        Timber.plant(Timber.DebugTree())
    }
}
