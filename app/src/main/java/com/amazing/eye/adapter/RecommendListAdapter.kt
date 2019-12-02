package com.amazing.eye.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.R

class RecommendListAdapter : RecyclerView.Adapter<RecommendListAdapter.MyViewholder>() {

    private  var dadaist: MutableList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recommendlistadapter_item, parent, false
        )
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return if (dadaist == null) 0 else dadaist!!.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.binding.executePendingBindings()
    }

    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
