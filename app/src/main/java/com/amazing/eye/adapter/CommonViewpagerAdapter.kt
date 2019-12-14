package com.amazing.eye.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


open class CommonViewpagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private lateinit var list:List<Fragment>

    constructor(fm: FragmentManager, list: List<Fragment>) : this(fm) {
        this.list = list
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

}