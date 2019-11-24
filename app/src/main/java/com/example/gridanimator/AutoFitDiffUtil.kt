package com.example.gridanimator

import androidx.recyclerview.widget.DiffUtil

class AutoFitDiffUtil(
    private val oldElementList: ArrayList<Int>,
    private val newElementList: ArrayList<Int>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldElementList[oldItemPosition] == newElementList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldElementList.size
    }

    override fun getNewListSize(): Int {
        return newElementList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldElementList[oldItemPosition] == newElementList[newItemPosition]
    }
}