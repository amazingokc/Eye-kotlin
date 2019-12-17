package com.amazing.eye.customView

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class ViewpagerScroll(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    var isScroll = false   //是否可以滚动

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (isScroll) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (isScroll) {
            super.onTouchEvent(ev)
        } else {
            true
        }
    }

    //关闭滚动效果
    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}