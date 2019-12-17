package com.amazing.eye

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.res.Configuration
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.ImageView
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.bean.VideoBean
import com.amazing.eye.databinding.ActivityVideoDetailBinding
import com.amazing.eye.utils.getTimeWithDuration
import com.amazing.eye.utils.loadNormalImage
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.Debuger
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_detail.*


class VideoDetailActivity : AppCompatActivity() {

    lateinit var videoDetailBean: VideoBean
    lateinit var orientationUtils: OrientationUtils
    var isPlay: Boolean = false
    var isPause: Boolean = false

    companion object {
        fun intentThere(
            context: AppCompatActivity,
            videoBean: VideoBean
        ) {
            val intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra("videoDetailBean", videoBean)
            context.startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityVideoDetailBinding>(
            this,
            R.layout.activity_video_detail
        )
        videoDetailBean =
            intent.getSerializableExtra("videoDetailBean") as VideoBean
        binding.videoDetailBean = videoDetailBean

        val time = String().getTimeWithDuration(videoDetailBean.duration!!)
        val category = videoDetailBean.category
        tv_video_time_detail_activity.text = "$category / $time"

        //外部辅助的旋转，帮助全屏
        orientationUtils = OrientationUtils(this, video_detail_activity)
        //初始化不打开外部的旋转
        orientationUtils.isEnable = false

        val imageView = ImageView(this)
        loadNormalImage(imageView, videoDetailBean.feed)
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption.setThumbImageView(imageView)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(true)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(videoDetailBean.playUrl)
            .setCacheWithPlay(false)
            .setVideoTitle(videoDetailBean.title)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any) {
                    super.onPrepared(url, *objects)
                    //开始播放了才能旋转和全屏
                    orientationUtils.isEnable = true
                    isPlay = true
                }

                override fun onQuitFullscreen(url: String?, vararg objects: Any) {
                    super.onQuitFullscreen(url, *objects)
                    Debuger.printfError("***** onQuitFullscreen **** " + objects[0])//title
                    Debuger.printfError("***** onQuitFullscreen **** " + objects[1])//当前非全屏player
                    orientationUtils.backToProtVideo()
                }
            }).setLockClickListener { view, lock ->
                orientationUtils.isEnable = !lock
            }.build(video_detail_activity)

        video_detail_activity.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils.resolveByClick()

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            video_detail_activity.startWindowFullscreen(this@VideoDetailActivity, true, true)
        }
    }

    override fun onBackPressed() {
        orientationUtils.backToProtVideo()
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }


    override fun onPause() {
        video_detail_activity.currentPlayer.onVideoPause()
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        video_detail_activity.currentPlayer.onVideoResume(false)
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            video_detail_activity.currentPlayer.release()
        }
        orientationUtils.releaseListener()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            video_detail_activity.onConfigurationChanged(
                this,
                newConfig,
                orientationUtils,
                true,
                true
            )
        }
    }
}
