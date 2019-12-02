package com.amazing.eye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.amazing.eye.adapter.CommonViewpagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recommendPosition = 0
    private val hotPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList: MutableList<Fragment> = mutableListOf(
            RecommendListFragment.newInstance(),
            HotListFragment.newInstance()
        )
        val adapter = CommonViewpagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragmentList
        )
        vp_content_mainactivty.offscreenPageLimit = fragmentList.size
        vp_content_mainactivty.adapter = adapter
        vp_content_mainactivty.currentItem = recommendPosition

        btn_recommend_mainactivity.setOnClickListener(this)
        btn_hot_mainactivity.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_recommend_mainactivity -> {
                btn_recommend_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_333))
                btn_hot_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_666))
                vp_content_mainactivty.currentItem = recommendPosition
            }
            R.id.btn_hot_mainactivity -> {
                btn_recommend_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_666))
                btn_hot_mainactivity.setTextColor(ContextCompat.getColor(this, R.color.text_333))
                vp_content_mainactivty.currentItem = hotPosition
            }
        }
    }
}
