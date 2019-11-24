package com.example.gridanimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.grid_element.view.*


class GridAdapter(private val context: Context, private val gridElements: ArrayList<Int>) :
    BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val TAG = "GridAdapter"

    fun addElements(gridElements: ArrayList<Int>) {
        this.gridElements.clear()
        this.gridElements.addAll(gridElements)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val gridViewHolder: GridViewHolder
        val params = UIUtilility.dpToPixels(context, MainActivity.elementSize.toFloat())
        if (convertView == null) {
            view = inflater.inflate(R.layout.grid_element, parent, false)
            view.layoutParams =
                ViewGroup.LayoutParams(params.toInt(), params.toInt())
            gridViewHolder = GridViewHolder(view)
            view.tag = gridViewHolder
        } else {
            view = convertView
            gridViewHolder = view.tag as GridViewHolder
        }

        gridViewHolder.itemView.setOnClickListener {
            Log.d(TAG, "Clicked: ${it.tag}")
            val oa1 = ObjectAnimator.ofFloat(gridViewHolder.itemView.frameLayout, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(gridViewHolder.itemView.frameLayout, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    gridElements.removeAt(position)
                    notifyDataSetChanged()
                    oa2.start()
                }
            })
            oa1.start()
        }

        gridViewHolder.itemView.gridText.text = gridElements[position].toString()
        return view
    }

    override fun getItem(position: Int): Any {
        Log.d(TAG, gridElements[position].toString())
        return gridElements[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        Log.d(TAG, gridElements.size.toString())
        return gridElements.size
    }

    fun removeElement(position: Int) {
        this.gridElements.removeAt(position)
    }

    inner class GridViewHolder(val itemView: View)
}