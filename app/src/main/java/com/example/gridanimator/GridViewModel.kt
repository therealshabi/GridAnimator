package com.example.gridanimator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GridViewModel : ViewModel() {

    val gridElementsLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()

    init {
        setElements()
    }

    fun setElements(elementsList: ArrayList<Int>) {
        gridElementsLiveData.value = elementsList
    }

    private fun setElements() {
        gridElementsLiveData.value = ArrayList((1..GridActivity.numOfElements).map {
            it
        })
    }

}