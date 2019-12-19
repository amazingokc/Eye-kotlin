package com.amazing.eye.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.R
import com.amazing.eye.detial.VideoDetailActivity
import com.amazing.eye.bean.HotBean
import com.amazing.eye.bean.VideoBean
import com.amazing.eye.utils.getTimeWithDuration
import kotlinx.android.synthetic.main.hotadapter_item.view.*

class HotAdapter(private var dadaist: MutableList<HotBean.ItemListBean.DataBean>) :
    RecyclerView.Adapter<HotAdapter.MyViewholder>() {

    lateinit var context: AppCompatActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        context = parent.context as AppCompatActivity
        val binding: ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.hotadapter_item, parent, false
        )
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.binding.setVariable(com.amazing.eye.BR.hotAdapter, this)
        holder.binding.setVariable(com.amazing.eye.BR.dataBean, dadaist[position])
        holder.binding.executePendingBindings()

        val category = dadaist[position].category
        val time = String().getTimeWithDuration(dadaist[position].duration)
        holder.binding.root.tv_category_time_hot_item.text = "$category / $time"
        holder.binding.root.setOnClickListener {
            val desc = dadaist[position].description
            val playUrl = dadaist[position].playUrl
            val blurred = dadaist[position].cover?.blurred
            val collect = dadaist[position].consumption?.collectionCount
            val share = dadaist[position].consumption?.shareCount
            val reply = dadaist[position].consumption?.replyCount
            val photoUrl = dadaist[position].cover?.feed
            val title = dadaist[position].title
            val duration = dadaist[position].duration
            val timeMillis = System.currentTimeMillis()
            val videoBean = VideoBean(
                dadaist[position].id,
                photoUrl,
                title,
                desc,
                duration,
                playUrl,
                category,
                blurred,
                "$collect",
                "$share",
                "$reply",
                timeMillis,
                false,
                1
            )
            VideoDetailActivity.intentThere(context, videoBean, 0, holder.binding.root.iv_hot_item)
        }
    }

    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}