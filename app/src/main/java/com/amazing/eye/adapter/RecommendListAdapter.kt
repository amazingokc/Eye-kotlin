package com.amazing.eye.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.R
import com.amazing.eye.bean.HomeBean

class RecommendListAdapter(private var dadaist: MutableList<HomeBean.IssueListBean>) :
    RecyclerView.Adapter<RecommendListAdapter.MyViewholder>() {


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
        holder.binding.setVariable(com.amazing.eye.BR.issueListBean, dadaist!![position])
        holder.binding.setVariable(com.amazing.eye.BR.recommendlistadapter, this)
        holder.binding.executePendingBindings()
    }

//    fun clickItem(issueListBean)

    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
