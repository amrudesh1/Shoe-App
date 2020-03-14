package com.udacity.shoestore.ui.signup

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.udacity.shoestore.R
import com.udacity.shoestore.data.local.prefs.LoginPrefs
import com.udacity.shoestore.databinding.FragmentSignUpBinding

class SignUp : Fragment() {
    lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    lateinit var loginPrefs: LoginPrefs
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSignUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return fragmentSignUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginPrefs = LoginPrefs(activity!!.applicationContext)
        navController = Navigation.findNavController(fragmentSignUpBinding.root)

        initClickListeners()

    }

    private fun initClickListeners() {
        fragmentSignUpBinding.signUpButton.setOnClickListener {
            if (validateDetails()) {
                loginPrefs.storeUser(
                    fragmentSignUpBinding.signUpEditText.text.toString(),
                    fragmentSignUpBinding.signUpPassword.text.toString()
                )
                navController.navigate(
                    R.id.login, null,
                    NavOptions.Builder().setPopUpTo(R.id.signUp, true).build()
                )
            }
        }

        fragmentSignUpBinding.alreadyHaveAAccount.setOnClickListener {
            navController.navigate(
                R.id.login, null,
                NavOptions.Builder().setPopUpTo(R.id.signUp, true).build()
            )
        }
    }


    fun validateDetails(): Boolean {
        var validated = true
        if (TextUtils.isEmpty(fragmentSignUpBinding.signUpEditText.text.toString())) {
            fragmentSignUpBinding.signUpEditText.error = "Please Enter a UserName"
            validated = false
        }

        if (TextUtils.isEmpty(fragmentSignUpBinding.signUpPassword.text.toString())) {
            fragmentSignUpBinding.signUpPassword.error = "Please Enter Your Password"
            validated = false

        }

        if (TextUtils.isEmpty(fragmentSignUpBinding.confirmSignupPassword.text.toString())) {
            fragmentSignUpBinding.confirmSignupPassword.error = "Please Enter Your Password"
            validated = false
        }
        Log.i(
            "Passwords",
            fragmentSignUpBinding.signUpPassword.text.toString() + " " + fragmentSignUpBinding.confirmSignupPassword.text.toString()
        )
        if (fragmentSignUpBinding.signUpPassword.text.toString() != fragmentSignUpBinding.confirmSignupPassword.text.toString()) {
            fragmentSignUpBinding.confirmSignupPassword.error = "Both Passwords are not same"
            validated = false

        }


        return validated

    }
}
