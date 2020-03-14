package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.udacity.shoestore.R
import com.udacity.shoestore.data.local.prefs.LoginPrefs
import com.udacity.shoestore.data.local.prefs.UserPrefrence
import com.udacity.shoestore.databinding.FragmentLoginBinding

class Login : Fragment() {

    lateinit var loginBinding: FragmentLoginBinding
    lateinit var prefs: UserPrefrence
    lateinit var navController: NavController
    lateinit var loginPrefs: LoginPrefs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = UserPrefrence(activity!!.applicationContext)
        loginPrefs = LoginPrefs(activity!!.applicationContext)
        navController = Navigation.findNavController(loginBinding.root)
        initClickListeners()


    }

    fun initClickListeners() {
        loginBinding.signInButton.setOnClickListener {
            if (verifyData()) {
                if (loginPrefs.isUserAuthenticated(
                        loginBinding.usernameLayout.text.toString()
                        , loginBinding.userPasswordLayout.text.toString()
                    )
                ) {
                    prefs.setName(loginBinding.usernameLayout.text.toString())
                    prefs.setPassword(loginBinding.userPasswordLayout.text.toString())
                    prefs.SET_LOGGED_IN(true)
                    navController.navigate(
                        R.id.splash, null,
                        NavOptions.Builder().setPopUpTo(R.id.login, true).build()
                    )
                } else {
                    Toast.makeText(activity, "Please Check Username or Password", Toast.LENGTH_LONG)
                        .show()
                }

            } else {
                Toast.makeText(activity, "Please Check Username or Password", Toast.LENGTH_LONG)
                    .show()
            }
        }

        loginBinding.createAccount.setOnClickListener {
            navController.navigate(
                R.id.signUp
            )

        }
    }

    fun verifyData(): Boolean {
        var dataVerified = true
        if (loginBinding.userPasswordLayout.text.isEmpty()) {
            loginBinding.userPasswordLayout.error = "Please Enter Password"
            dataVerified = false
        }
        if (loginBinding.usernameLayout.text.isEmpty()) {
            loginBinding.usernameLayout.error = "Please Enter UserName"
            dataVerified = false
        }

        return dataVerified
    }
}
