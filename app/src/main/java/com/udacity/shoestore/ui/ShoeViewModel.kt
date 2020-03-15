package com.udacity.shoestore.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.models.Shoe

class ShoeViewModel : ViewModel() {
    var shoeData: MutableLiveData<List<Shoe>> = MutableLiveData()
    var shoeList: ArrayList<Shoe> = arrayListOf()

    init {
        Log.i("ShoeViewModel", "Created")
    }

    fun getAllShoes(): LiveData<List<Shoe>> {
        return shoeData
    }

    fun addShoe(shoe: Shoe) {
        shoeList.add(shoe)
        shoeData.value = shoeList
        Log.i("ShoeDataFromModel", (shoeData.value as ArrayList<Shoe>).size.toString())

    }
}