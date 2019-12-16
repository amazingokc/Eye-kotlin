package com.amazing.eye.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.amazing.eye.R
import com.amazing.eye.VideoDetailActivity
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.utils.getTimeWithDuration
import com.amazing.eye.utils.loadNormalImage
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
//        //时长
//        holder.binding.root.tv_time_recommend_item.text =
//            String().getTimeWithDuration(bean.data?.duration!!)

        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.let {
            it.setUp(bean.data!!.playUrl, false, null, null)

            val imageView = ImageView(context)
            loadNormalImage(imageView,bean.data?.cover?.feed)

            it.thumbImageView = imageView
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

        holder.binding.root.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                VideoDetailActivity.intentThere(context, bean)
            }

        })
    }


    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
