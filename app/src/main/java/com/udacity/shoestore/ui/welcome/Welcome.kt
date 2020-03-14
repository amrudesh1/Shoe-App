package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentSplashBinding

class Welcome : Fragment() {
    lateinit var splashView: View
    lateinit var listItemBinding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_splash,
            container,
            false
        )

        splashView = listItemBinding.root
        return splashView
    }

}
