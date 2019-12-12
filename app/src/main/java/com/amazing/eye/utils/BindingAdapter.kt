package com.amazing.eye.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgUrlUrl")
fun loadNormalImage(
    imageView: ImageView,
    url: String?
) {
    ImageLoaderUtil.getInstance().loadNormalImg(imageView, url, null, null)
}

@BindingAdapter("imgUrlCircleUrl")
fun loadCircleImage(imageView: ImageView, url: String) {
    ImageLoaderUtil.getInstance().loadCircleImg(imageView, url, null, null)
}

fun String.getTimeWithDuration(duration: Long): String {
    val minute = duration.div(60)
    val second = duration.minus((minute.times(60)))
    val realMinute: String
    val realSecond: String
    realMinute = if (minute < 10) {
        "0$minute"
    } else {
        minute.toString()
    }
    realSecond = if (second < 10) {
        "0$second"
    } else {
        second.toString()
    }
    return "$realMinute:$realSecond"
}