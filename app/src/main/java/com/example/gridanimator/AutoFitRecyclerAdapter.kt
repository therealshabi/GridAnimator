package com.example.gridanimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_element.view.*

class AutoFitRecyclerAdapter(private val gridElements: ArrayList<Int>) :
    RecyclerView.Adapter<AutoFitRecyclerAdapter.AutoFitViewHolder>() {

    inner class AutoFitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoFitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_element, parent, false)
        val params = UIUtilility.dpToPixels(parent.context, MainActivity.elementSize.toFloat())
        view.layoutParams = ViewGroup.LayoutParams(params.toInt(), params.toInt())
        return AutoFitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gridElements.size
    }

    override fun onBindViewHolder(holder: AutoFitViewHolder, position: Int) {
        holder.itemView.gridText.text = gridElements[position].toString()
        holder.itemView.tag = gridElements[position]
        holder.itemView.setOnClickListener {
            val oldList = ArrayList<Int>()
            oldList.addAll(gridElements)
            gridElements.remove(it.tag)
            val oa1 = ObjectAnimator.ofFloat(holder.itemView.frameLayout, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(holder.itemView.frameLayout, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    val diffResult = DiffUtil
                        .calculateDiff(AutoFitDiffUtil(oldList, gridElements))
                    diffResult.dispatchUpdatesTo(this@AutoFitRecyclerAdapter)
                    oa2.start()
                }
            })
            oa1.start()
        }
    }
}