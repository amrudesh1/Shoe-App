package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.main.fragment_splash.*

class Welcome : Fragment() {
    lateinit var splashBinding: FragmentSplashBinding
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_splash,
            container,
            false
        )
        return splashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(splashBinding.root)
        initClickListeners()
    }

    private fun initClickListeners() {
        splashBinding.splashProceedButton.setOnClickListener {
            navController.navigate(
                R.id.instructions, null,
                NavOptions.Builder().setPopUpTo(R.id.splash, true).build()
            )
        }
    }
}
