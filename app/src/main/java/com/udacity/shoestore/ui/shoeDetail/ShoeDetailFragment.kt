package com.udacity.shoestore.ui.shoeDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    lateinit var shoe: Shoe
    lateinit var shoeDetailBinding: FragmentShoeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        shoeDetailBinding.shoe = this.shoe
        return shoeDetailBinding.root
    }

    private fun setData() {
        shoeDetailBinding.shoeDetailName.text = shoe.name
        shoeDetailBinding.shoeDetailBrand.text = shoe.company
        shoeDetailBinding.shoeDetailDescription.text = shoe.description
        shoeDetailBinding.shoeDetailSize.text = shoe.size.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoe = arguments!!.getParcelable("shoe")!!
    }
}
