package com.amazing.eye.detial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.eye.BaseFragment
import com.amazing.eye.R
import com.amazing.eye.adapter.CommentListAdapter
import com.amazing.eye.viewmodel.CommentListVm
import kotlinx.android.synthetic.main.fragment_comment_list.*

private const val ARG_PARAM1 = "param1"

/**
 * 评论列表
 */
class CommentListFragment : BaseFragment() {
    private var videoId: Int? = null
    private var commentListVm: CommentListVm? = null
    private lateinit var commentListAdapter: CommentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //下拉刷新
        refresh_comment_fragment.setOnRefreshListener {
            videoId?.let {
                commentListVm!!.loadData(it, false)
            }
        }
        //加载更多
        rv_comment_list_fragment.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (commentListVm!!.isHasMore()) {
                    val layoutManager: LinearLayoutManager =
                        recyclerView.layoutManager as LinearLayoutManager
                    val lastPositon = layoutManager.findLastVisibleItemPosition()
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == commentListVm!!.getDataList().size - 1) {
                        videoId?.let {
                            commentListVm!!.loadData(it, true)
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (commentListVm == null) {
            commentListVm = createViewModel(CommentListVm::class.java)
            registerViewModelObserver(commentListVm!!)
            //初始化列表适配
            commentListAdapter = CommentListAdapter(commentListVm!!.getDataList())
            rv_comment_list_fragment.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.VERTICAL, false
            )
            rv_comment_list_fragment.adapter = commentListAdapter

            videoId?.let {
                refresh_comment_fragment.isRefreshing = true
                commentListVm!!.loadData(it, false)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(videoId: Int?) =
            CommentListFragment().apply {
                arguments = Bundle().apply {
                    if (videoId != null) {
                        putInt(ARG_PARAM1, videoId)
                    }
                }
            }
    }

    override fun onApiSuccessCallBack(any: Any) {
        super.onApiSuccessCallBack(any)
        commentListAdapter.notifyDataSetChanged()
        refresh_comment_fragment.isRefreshing = false
    }

    override fun onApiErrorCallBack(any: Any) {
        super.onApiErrorCallBack(any)
        refresh_comment_fragment.isRefreshing = false
    }
}
