package com.amazing.eye

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.eye.viewmodel.HomeVm


class HotListFragment : Fragment() {

    var homeVm = HomeVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeVm.loadData()
    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {

        @JvmStatic
        fun newInstance() = HotListFragment()
    }
}
