package com.amazing.eye

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.amazing.eye.adapter.CommonViewpagerAdapter
import com.amazing.eye.home.RecommendListFragment
import com.amazing.eye.hot.HotListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recommendPosition = 0
    private val hotPosition = 1
    private val homeItem = 0
    private val hotItem = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList: MutableList<Fragment> = mutableListOf(
            RecommendListFragment.newInstance(),
            HotListFragment.newInstance()
        )
        val adapter = CommonViewpagerAdapter(
            supportFragmentManager,
            fragmentList
        )
        vp_content_mainactivty.offscreenPageLimit = fragmentList.size
        vp_content_mainactivty.adapter = adapter
        vp_content_mainactivty.currentItem = recommendPosition
        vp_content_mainactivty.isScroll = true
        vp_content_mainactivty.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setBottomButton(position)
            }

        })

        btn_recommend_mainactivity.setOnClickListener(this)
        btn_hot_mainactivity.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_recommend_mainactivity -> {
                setBottomButton(homeItem)
            }
            R.id.btn_hot_mainactivity -> {
                setBottomButton(hotItem)
            }
        }
    }

    //设置底部按钮状态
    private fun setBottomButton(item: Int) {
        when (item) {
            homeItem -> {
                btn_recommend_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_333))
                btn_hot_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_666))
                vp_content_mainactivty.currentItem = recommendPosition
            }
            hotItem -> {
                btn_recommend_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_666))
                btn_hot_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_333))
                vp_content_mainactivty.currentItem = hotPosition
            }
        }
    }
}
