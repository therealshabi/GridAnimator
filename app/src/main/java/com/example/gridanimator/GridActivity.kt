package com.example.gridanimator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_grid.*


class GridActivity : AppCompatActivity() {

    companion object {
        var elementSize = AppConfig.DEFAULT_ELEMENT_SIZE
        var elementSpacing = AppConfig.DEFAULT_ELEMENT_SPACING
        var elementAnimationDelay = AppConfig.DEFAULT_ELEMENT_ANIMATION_DELAY
        var numOfElements = AppConfig.DEFAULT_ELEMENT_NUM
    }

    private lateinit var gridAdapter: AutoFitRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        gridAdapter = AutoFitRecyclerAdapter(ArrayList((1..numOfElements).map {
            it
        }))

        val itemAnimator = DefaultItemAnimator()
        itemAnimator.moveDuration = elementAnimationDelay.toLong()

        gridRecyclerView.apply {
            this.itemAnimator = itemAnimator
            this.layoutManager = GridLayoutManager(this@GridActivity, calculateColumns())
            this.adapter = gridAdapter
        }

        resetButton.setOnClickListener {
            gridAdapter.reset()
        }
    }

    private fun calculateColumns(): Int {
        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density
        val width: Int = displayMetrics.widthPixels
        var columns = (width / (elementSize * density)).toInt()
        if (columns == 0 || columns == 1) columns = 2
        return columns
    }
}
