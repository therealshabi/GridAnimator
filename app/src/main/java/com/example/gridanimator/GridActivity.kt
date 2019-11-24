package com.example.gridanimator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_grid.*


class GridActivity : AppCompatActivity() {

    companion object {
        var elementSize = 80
        var elementSpacing = 2
        var elementAnimationDelay = 250
        var numOfElements = 20
    }

    private lateinit var gridAdapter: AutoFitRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        gridAdapter = AutoFitRecyclerAdapter(ArrayList((1..20).map {
            it
        }))
        val itemAnimator = DefaultItemAnimator()
        itemAnimator.moveDuration = 1000
        gridRecyclerView.itemAnimator = itemAnimator
        gridRecyclerView.layoutManager = GridLayoutManager(this, calculateColumns())
        gridRecyclerView.adapter = gridAdapter
    }

    private fun calculateColumns(): Int {
        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density
        val width: Int = displayMetrics.widthPixels
        Log.d("MainActivity", "$density $width $elementSize")
        var columns = (width / (elementSize * density)).toInt()
        if (columns == 0 || columns == 1) columns = 2
        return columns
    }
}