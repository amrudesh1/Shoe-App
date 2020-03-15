package com.udacity.shoestore.ui.shoe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentShoeBinding
import com.udacity.shoestore.ui.ShoeViewModel

/**
 * A simple [Fragment] subclass.
 */
class ShoeFragment : Fragment() {

    lateinit var shoeBinding: FragmentShoeBinding
    lateinit var shoeViewModel: ShoeViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe, container, false)
        return shoeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ShoeData", "Hi")
        initClickListeners()
        navController = Navigation.findNavController(shoeBinding.root)
        shoeViewModel.getAllShoes().observe(viewLifecycleOwner, Observer {
            addData(it)
        })
    }

    fun addData(shoes: List<Shoe>) {
        shoes.forEach {
            shoeBinding.mainView.addView(addView(it))
        }
    }

    fun addView(shoeData: Shoe): View {
        val inflater = LayoutInflater.from(activity)
        val view: View =
            inflater.inflate(R.layout.shoe_item, shoeBinding.mainView, false)
        view.findViewById<TextView>(R.id.shoe_name).text = shoeData.name
        view.findViewById<TextView>(R.id.shoe_size).text =
            "Shoe Size:" + " " + shoeData.size.toString()
        view.findViewById<TextView>(R.id.shoe_brand).text = "Brand:" + " " + shoeData.company
        view.findViewById<TextView>(R.id.shoe_description).text = shoeData.description
        view.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putParcelable("shoe", shoeData)
            navController.navigate(R.id.shoeDetailFragment, bundle)
        }
        return view

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoeViewModel = activity?.let { ViewModelProvider(it).get(ShoeViewModel::class.java) }!!

    }

    private fun initClickListeners() {
        shoeBinding.addFloatingButton.setOnClickListener {
            navController.navigate(
                R.id.addShoe
            )
        }
    }
}
