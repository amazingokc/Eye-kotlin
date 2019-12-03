package com.amazing.eye.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgUrlCircleUrl", "placeHolder", "error")
public fun ImageView.loadNormalImage( url: String, placeholder: Drawable?=null, error: Drawable?=null) {
    ImageLoaderUtil.getInstance().loadNormalImg(this, url, placeholder, error)
}

//@BindingAdapter("imgUrlCircleUrl")
//public fun loadCircleImage(imageView: ImageView, url: String) {
//    ImageLoaderUtil.getInstance().loadCircleImg(imageView, url, null, null)
//}