package com.amazing.eye.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

open class CommonViewpagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    private lateinit var list: List<Fragment>
    private var titleList: List<String>? = null

    constructor(fm: FragmentManager, behavior: Int, list: List<Fragment>) : this(fm, behavior) {
        this.list = list
    }

    constructor(
        fm: FragmentManager,
        behavior: Int,
        list: List<Fragment>,
        titleList: List<String>
    ) : this(fm, behavior) {
        this.list = list
        this.titleList = titleList
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList != null) {
            titleList!![position]
        } else {
            ""
        }
    }

    override fun getCount(): Int {
        return list.size
    }

}