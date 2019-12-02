package com.amazing.eye

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.eye.adapter.RecommendListAdapter
import com.amazing.eye.viewmodel.HomeVm
import kotlinx.android.synthetic.main.fragment_recommend_list.*


/**
 *
 */
class RecommendListFragment : BaseFragment() {

    var homeVm: HomeVm? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = RecommendListAdapter()
        rv_list_recommend.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv_list_recommend.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendListFragment()
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResumeonResume", "RecommendListFragment")
        if (homeVm == null) {
            homeVm = HomeVm()
            homeVm!!.loadData()
        }
    }
}
