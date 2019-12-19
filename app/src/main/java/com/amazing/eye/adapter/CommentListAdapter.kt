package com.amazing.eye.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.R
import com.amazing.eye.bean.CommentBean
import com.amazing.eye.bean.HotBean

class CommentListAdapter(var dadaist: MutableList<CommentBean.ReplyListBean>) :
    RecyclerView.Adapter<CommentListAdapter.MyViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.commentlistadapter_item, parent, false
        )
        return MyViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.databinding.setVariable(com.amazing.eye.BR.replyListBean, dadaist[position])
        holder.databinding.executePendingBindings()
    }

    class MyViewHolder(val databinding: ViewDataBinding) : RecyclerView.ViewHolder(databinding.root)
}