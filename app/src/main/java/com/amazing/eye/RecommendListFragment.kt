package com.amazing.eye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.eye.adapter.RecommendListAdapter
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.viewmodel.HomeVm
import kotlinx.android.synthetic.main.fragment_recommend_list.*


/**
 *
 */
class RecommendListFragment : BaseFragment() {

    lateinit var adapter: RecommendListAdapter
    private var homeVm: HomeVm? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommend_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (homeVm == null) {
            homeVm = createViewModel(HomeVm::class.java)
            registerViewModelObserver(homeVm!!)
            adapter = RecommendListAdapter(homeVm!!.getDatas())
            rv_list_recommend.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            rv_list_recommend.adapter = adapter
            homeVm!!.loadData(false)
        }
    }

    override fun onApiSuccessCallBack(any: Any) {
        super.onApiSuccessCallBack(any)
        adapter.notifyDataSetChanged()
    }

    override fun onApiErrorCallBack(any: Any) {
        super.onApiErrorCallBack(any)
        Toast.makeText(activity, any.toString(), Toast.LENGTH_SHORT).show()
    }

}
