package com.amazing.eye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.eye.adapter.RecommendListAdapter
import com.amazing.eye.viewmodel.HomeVm
import kotlinx.android.synthetic.main.fragment_recommend_list.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.shuyu.gsyvideoplayer.GSYVideoManager
import java.util.regex.Pattern


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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refresh_recommend.setOnRefreshListener { homeVm?.loadData(false) }

        rv_list_recommend.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == homeVm!!.getDatas().size - 1) {
                    homeVm?.loadData(true)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager is LinearLayoutManager && GSYVideoManager.instance().playPosition >= 0) {
                    //获取最后一个可见view的位置
                    val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                    //获取第一个可见view的位置
                    val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                    //获取可见view的总数
                    val visibleItemCount = layoutManager.childCount

                    if (_firstItemPosition < firstItemPosition) {
                        _firstItemPosition = firstItemPosition
                        _lastItemPosition = lastItemPosition
                        GCView()
                        fistView = recyclerView.getChildAt(0)
                        lastView = recyclerView.getChildAt(visibleItemCount - 1)
                    } else if (_lastItemPosition > lastItemPosition) {
                        _firstItemPosition = firstItemPosition
                        _lastItemPosition = lastItemPosition
                        GCView()
                        fistView = recyclerView.getChildAt(0)
                        lastView = recyclerView.getChildAt(visibleItemCount - 1)
                    }
                }
            }

        })

    }

    private fun GCView() {
        GSYVideoManager.releaseAllVideos()
        adapter.notifyDataSetChanged()
    }

    var _firstItemPosition = -1
    var _lastItemPosition: Int = 0
    var fistView: View? = null
    var lastView: View? = null

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
        refresh_recommend.isRefreshing = false
        adapter.notifyDataSetChanged()
    }

    override fun onApiErrorCallBack(any: Any) {
        super.onApiErrorCallBack(any)
        refresh_recommend.isRefreshing = false
        Toast.makeText(activity, any.toString(), Toast.LENGTH_SHORT).show()
    }

}
