package com.udacity.shoestore.ui.instructions

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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentShoeBinding

/**
 * A simple [Fragment] subclass.
 */
class Instructions : Fragment() {
    lateinit var instructionsBinding: FragmentInstructionsBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        instructionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        return instructionsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(instructionsBinding.root)
        initClickLisners()
    }

    private fun initClickLisners() {
        instructionsBinding.splashProceedButton.setOnClickListener {
            navController.navigate(
                R.id.shoeFragment, null,
                NavOptions.Builder().setPopUpTo(R.id.instructions, true).build()
            )
        }
    }
}
