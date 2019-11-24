package com.example.gridanimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        var elementSize = 80
    }

    private lateinit var gridAdapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateColumns()

        gridAdapter = GridAdapter(this, ArrayList((1..20).map {
            it
        }))
        gridView.adapter = gridAdapter

        gridView.onItemClickListener = OnItemClickListener { _, view, position, id ->
            Log.d("MainActivity", "Clicked: $position")
            val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    gridAdapter.removeElement(position)
                    gridAdapter.notifyDataSetChanged()
                    oa2.start()
                }
            })
            oa1.start()
        }
    }

    private fun calculateColumns() {
        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density
        val width: Int = displayMetrics.widthPixels
        Log.d("MainActivity", "$density $width $elementSize")
        var columns = (width / (elementSize * density)).toInt()
        if (columns == 0 || columns == 1) columns = 2
        gridView.numColumns = columns
    }
}
