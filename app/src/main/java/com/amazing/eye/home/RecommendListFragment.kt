package com.amazing.eye.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amazing.eye.adapter.RecommendListAdapter
import com.amazing.eye.viewmodel.HomeVm
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.BaseFragment
import com.amazing.eye.R
import com.shuyu.gsyvideoplayer.GSYVideoManager


/**
 * 推荐列表
 */
class RecommendListFragment : BaseFragment() {

    private lateinit var adapter: RecommendListAdapter
    private var homeVm: HomeVm? = null
    private lateinit var refresh_recommend: SwipeRefreshLayout
    private lateinit var rv_list_recommend: RecyclerView


    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recommend_list, container, false)
        refresh_recommend = view.findViewById(R.id.refresh_recommend)
        rv_list_recommend = view.findViewById(R.id.rv_list_recommend)
        return view
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
                    //大于0说明有播放
                    if (GSYVideoManager.instance().playPosition >= 0) {
                        //当前播放的位置
                        val position = GSYVideoManager.instance().playPosition
                        //对应的播放列表TAG
                        if (GSYVideoManager.instance().playTag == RecommendListAdapter.TAG
                            && (position < firstItemPosition || position > lastItemPosition)
                        ) {
                            if (GSYVideoManager.isFullState(activity)) {
                                return
                            }
                            //如果滑出去了上面和下面就是否，和今日头条一样
                            GSYVideoManager.releaseAllVideos()
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }

        })

    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
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
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
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
