package com.amazing.eye.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.eye.BaseFragment

import com.amazing.eye.R
import com.amazing.eye.adapter.HotAdapter
import com.amazing.eye.viewmodel.HotVm
import kotlinx.android.synthetic.main.fragment_hot.*


/**
 */
class HotFragment : BaseFragment() {

    lateinit var hotAdapter: HotAdapter
    private var hotVm: HotVm? = null
    private var strategy: String = ""
    var isGetdata: Boolean = false
    private val strategy_key = "strategy_key"
    private val isGetdata_key = "isGetdata_key"

    companion object {
        @JvmStatic
        fun newInstance(strategy: String, isGetdata:Boolean) = HotFragment().apply {
            arguments = Bundle().apply {
                putString(strategy_key, strategy)
                putBoolean(isGetdata_key, isGetdata)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            strategy = it.getString(strategy_key, "")
            isGetdata = it.getBoolean(isGetdata_key, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (hotVm == null && isGetdata) {
            hotVm = createViewModel(HotVm::class.java)
            registerViewModelObserver(hotVm!!)
            rv_hot.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            hotAdapter = HotAdapter(hotVm!!.getDatas())
            rv_hot.adapter = hotAdapter

            hotVm!!.loadData(strategy)
        }
    }

    override fun onApiSuccessCallBack(any: Any) {
        super.onApiSuccessCallBack(any)
        hotAdapter.notifyDataSetChanged()
    }

    override fun onApiErrorCallBack(any: Any) {
        super.onApiErrorCallBack(any)
        Toast.makeText(activity, any.toString(), Toast.LENGTH_SHORT).show()
    }

}
