package com.amazing.eye.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

open class CommonViewpagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    private lateinit var list:List<Fragment>

    constructor(fm: FragmentManager, behavior: Int, list: List<Fragment>) : this(fm, behavior) {
        this.list = list
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

}