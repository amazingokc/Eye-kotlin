package com.amazing.eye.utils

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*

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

@SuppressLint("SimpleDateFormat")
fun String.getCustomTime(time: Long): String {

    val date = Date()
    date.time = java.lang.Long.parseLong(time.toString())

    val template = SimpleDateFormat("dd/MM HH:mm")
    return template.format(date)
}