package com.amazing.eye.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.ApplicationContext
import com.amazing.eye.R
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.utils.LLog
import com.amazing.eye.utils.getTimeWithDuration
import com.amazing.eye.utils.loadNormalImage
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import kotlinx.android.synthetic.main.recommendlistadapter_item.view.*
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException

class RecommendListAdapter(private var dadaist: MutableList<HomeBean.IssueListBean.ItemListBean>) :
    RecyclerView.Adapter<RecommendListAdapter.MyViewholder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        context = parent.context
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recommendlistadapter_item, parent, false
        )
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.binding.setVariable(com.amazing.eye.BR.itemListBean, dadaist[position])
        holder.binding.setVariable(com.amazing.eye.BR.recommendlistadapter, this)
        holder.binding.executePendingBindings()
        val bean = dadaist[position]
        //时长
        holder.binding.root.tv_time_recommend_item.text =
            String().getTimeWithDuration(bean.data?.duration!!)

        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.let {
            it.setUp(bean.data!!.playUrl, false, null, null)

            val imageView = ImageView(context)
            imageView.loadNormalImage(bean.data?.cover?.feed, null, null)

            it.thumbImageView = imageView
            it.setThumbPlay(true)
            it.titleTextView.text = bean.data!!.title
            //隐藏返回按钮
            it.backButton.visibility = View.GONE

            it.gsyVideoManager.setListener(object : GSYMediaPlayerListener {
                override fun onAutoCompletion() {
                }

                override fun onPrepared() {
                    holder.binding.root.rl_bottom_recommend_item.visibility = View.GONE
                }

                override fun onCompletion() {
                    holder.binding.root.rl_bottom_recommend_item.visibility = View.VISIBLE
                }

                override fun onVideoPause() {
                }

                override fun onSeekComplete() {
                }

                override fun onInfo(what: Int, extra: Int) {
                }

                override fun onVideoSizeChanged() {
                }

                override fun onBufferingUpdate(percent: Int) {
                }

                override fun onBackFullscreen() {
                }

                override fun onError(what: Int, extra: Int) {
                }

                override fun onVideoResume() {
                }

                override fun onVideoResume(seek: Boolean) {
                }

            })
        }
    }


    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
