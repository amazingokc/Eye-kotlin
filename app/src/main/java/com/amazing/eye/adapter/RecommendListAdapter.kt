package com.amazing.eye.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.amazing.eye.ApplicationContext
import com.amazing.eye.R
import com.amazing.eye.VideoDetailActivity
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.bean.VideoBean
import com.amazing.eye.utils.loadNormalImage
import kotlinx.android.synthetic.main.activity_video_detail.*
import kotlinx.android.synthetic.main.recommendlistadapter_item.view.*

class RecommendListAdapter(private var dadaist: MutableList<HomeBean.IssueListBean.ItemListBean>) :
    RecyclerView.Adapter<RecommendListAdapter.MyViewholder>() {

    companion object {
        const val TAG = "RecommendListAdapter123"
    }

    lateinit var context: AppCompatActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        context = parent.context as AppCompatActivity
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recommendlistadapter_item, parent, false
        )
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.binding.setVariable(com.amazing.eye.BR.itemListBean, dadaist[position])
        holder.binding.setVariable(com.amazing.eye.BR.recommendlistadapter, this)
        holder.binding.executePendingBindings()
        val bean = dadaist[position]
        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.let {
            it.setUp(bean.data!!.playUrl, false, null, null)

            val imageView = ImageView(context)
            loadNormalImage(imageView, bean.data?.cover?.feed)

            it.thumbImageView = imageView
            it.thumbImageView.transitionName =
                ApplicationContext.getString(R.string.transitionName_video)
            it.setThumbPlay(true)
            it.titleTextView.text = bean.data!!.title
            //隐藏返回按钮
            it.backButton.visibility = View.GONE
            //设置全屏按键功能
            it.fullscreenButton.setOnClickListener { _ ->
                it.startWindowFullscreen(context, false, true)
            }
            //防止错位设置
            it.playTag = TAG
            it.playPosition = position
            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
            it.isAutoFullWithSize = true
            //音频焦点冲突时是否释放
            it.isReleaseWhenLossAudio = false
            //全屏动画
            it.isShowFullAnimation = true
            //小屏时不触摸滑动
            it.setIsTouchWiget(false)
        }

        holder.binding.root.setOnClickListener {
            val desc = bean.data?.description
            val duration = bean.data?.duration
            val playUrl = bean.data?.playUrl
            val blurred = bean.data?.cover?.blurred
            val collect = bean.data?.consumption?.collectionCount
            val share = bean.data?.consumption?.shareCount
            val reply = bean.data?.consumption?.replyCount
            val photo = bean.data?.cover?.feed
            val title = bean.data?.title
            val category = bean.data?.category
            val time = System.currentTimeMillis()
            val isPlaying = holder.binding.root.gsy_player_recommend_item.isInPlayingState
            val currentPosition = holder.binding.root.gsy_player_recommend_item.currentPosition
            val videoBean = VideoBean(
                photo,
                title,
                desc,
                duration,
                playUrl,
                category,
                blurred,
                collect,
                share,
                reply,
                time,
                isPlaying,
                currentPosition
            )
            VideoDetailActivity.intentThere(
                context,
                videoBean,
                holder.binding.root.gsy_player_recommend_item
            )
        }
    }


    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
