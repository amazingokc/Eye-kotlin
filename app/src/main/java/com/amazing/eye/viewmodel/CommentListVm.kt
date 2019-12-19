package com.amazing.eye.viewmodel

import android.text.TextUtils
import com.amazing.eye.bean.CommentBean
import com.amazing.eye.model.CommentListModel

class CommentListVm : BaseViewModel() {

    private var dataList = mutableListOf<CommentBean.ReplyListBean>()
    private var isLoadMore = false
    private var isHasMore = false
    private var lastId = ""

    fun getDataList(): MutableList<CommentBean.ReplyListBean> {
        return dataList
    }

    fun isHasMore(): Boolean {
        return isHasMore
    }

    fun loadData(videoId: Int, isLoadMore: Boolean) {
        if (baseModel == null) {
            baseModel = CommentListModel()
        }
        this.isLoadMore = isLoadMore
        if (!isLoadMore) lastId = ""
        (baseModel as CommentListModel).videoId = videoId
        (baseModel as CommentListModel).lastId = lastId
        super.loadData()
    }

    override fun onSuccess(any: Any) {
        if (any is CommentBean) {
            isHasMore = !TextUtils.isEmpty(any.nextPageUrl)
            if (isLoadMore)
                any.replyList?.let { dataList.addAll(it) }
            else
                dataList.clear()
            dataList.addAll(any.replyList as MutableList<CommentBean.ReplyListBean>)
            if (dataList.size > 0) {
                lastId = dataList[dataList.size - 1].sequence.toString()
            }
            super.onSuccess(any)
        }
    }
}