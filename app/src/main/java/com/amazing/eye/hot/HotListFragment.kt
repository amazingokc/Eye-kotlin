package com.amazing.eye.hot

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.amazing.eye.R
import com.amazing.eye.adapter.CommonViewpagerAdapter
import com.amazing.eye.home.RecommendListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hot_list.*


class HotListFragment : Fragment() {

    private lateinit var weekFragment: HotFragment

    companion object {
        @JvmStatic
        fun newInstance() = HotListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hot_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weekFragment = HotFragment.newInstance("weekly", false)
        val fragmentList: MutableList<Fragment> = mutableListOf(
            weekFragment,
            HotFragment.newInstance("monthly", true),
            HotFragment.newInstance("historical", true)
        )

        val titleList: MutableList<String> = mutableListOf(
            "周排行",
            "月排行",
            "总排行"
        )
        val adapter = CommonViewpagerAdapter(
            activity!!.supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragmentList,
            titleList
        )

        vp_hot.offscreenPageLimit = fragmentList.size
        vp_hot.currentItem = 0
        vp_hot.isScroll = true
        vp_hot.adapter = adapter
        tablayout_hot.setupWithViewPager(vp_hot)
    }

    override fun onResume() {
        super.onResume()
        if (!weekFragment.isGetdata) {
            weekFragment.isGetdata = true
            weekFragment.onResume()
        }
    }
}
