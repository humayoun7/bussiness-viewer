package com.humayoun.businessviewer.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humayoun.businessviewer.R
import com.humayoun.businessviewer.model.Business

/**
 * Adpater to render stackview since stackview is extension of recycler view
 * */

class BusinessAdapter(
    private val context: Context,
    private var businesses: ArrayList<Business>):
    RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRating: TextView = itemView.findViewById(R.id.txtRating)
        val ivDisplay: ImageView = itemView.findViewById(R.id.ivDisplay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_business, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = businesses[position]

        with(holder) {
            txtName.text = business.name
            txtRating.text = business.rating.toString()

            Glide.with(context)
                .load(business.imageUrl)
                .into(ivDisplay)
        }
    }

    override fun getItemCount(): Int {
        return businesses.size
    }

    fun addItems(newItems: List<Business>) {
        val start = businesses.size
        businesses.addAll(newItems)
        notifyItemRangeChanged(start, newItems.size)
    }

}