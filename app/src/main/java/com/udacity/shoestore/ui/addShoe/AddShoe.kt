package com.udacity.shoestore.ui.addShoe

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.databinding.FragmentShoeBinding
import com.udacity.shoestore.ui.ShoeViewModel


class AddShoe : Fragment() {
    lateinit var addShoeBinding: FragmentAddShoeBinding
    lateinit var navController: NavController
    lateinit var shoeViewModel: ShoeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addShoeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)
        return addShoeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(addShoeBinding.root)
        initClickListeners()

    }

    private fun initClickListeners() {
        addShoeBinding.addShoeButton.setOnClickListener {
            if (validateDetails()) {
                shoeViewModel.addShoe(getData())
                navController.navigate(
                    R.id.shoeFragment, null,
                    NavOptions.Builder().setPopUpTo(R.id.addShoe, true).build()
                )
            }
        }
    }

    private fun getData(): Shoe {
        return Shoe(
            addShoeBinding.shoeNameEditText.text.toString(),
            addShoeBinding.shoeSizeEditText.text.toString().toDouble(),
            addShoeBinding.shoeBrandEditText.text.toString(),
            addShoeBinding.shoeDescriptionEditText.text.toString()
        )
    }

    private fun validateDetails(): Boolean {
        var validated = true
        if (TextUtils.isEmpty(addShoeBinding.shoeNameEditText.text)) {
            addShoeBinding.shoeNameEditText.setError("Please Enter Name")
            validated = false
        }

        if (TextUtils.isEmpty(addShoeBinding.shoeSizeEditText.text)) {
            addShoeBinding.shoeSizeEditText.setError("Please Enter Size")
            validated = false
        }

        if (TextUtils.isEmpty(addShoeBinding.shoeBrandEditText.text)) {
            addShoeBinding.shoeBrandEditText.setError("Please Enter Brand Name")
            validated = false
        }

        if (TextUtils.isEmpty(addShoeBinding.shoeDescriptionEditText.text)) {
            addShoeBinding.shoeDescriptionEditText.setError("Please Enter Description")
            validated = false
        }

        return validated

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoeViewModel =
            activity?.let { ViewModelProvider(it).get(ShoeViewModel::class.java) }!!

    }
}
