package com.amazing.eye.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgUrlCircleUrl", "placeHolder", "error")
fun ImageView.loadNormalImage(
    url: String?,
    placeholder: Drawable? = null,
    error: Drawable? = null
) {
    ImageLoaderUtil.getInstance().loadNormalImg(this, url, placeholder, error)
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