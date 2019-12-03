package com.amazing.eye.viewmodel

import com.amazing.eye.bean.BaseBean
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.model.HomeModel

class HomeVm : BaseViewModel(HomeModel()) {

    var dadaist = mutableListOf<HomeBean.IssueListBean>()

    var isLoadMore = false

    fun getDatas(): MutableList<HomeBean.IssueListBean> {
        return dadaist
    }

    fun loadData(isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore
        loadData()
    }


    override fun onSuccess(baseBean: BaseBean) {
        if (baseBean is HomeBean) {
            baseBean.issueList?.let {
                if (!isLoadMore) {
                    dadaist.clear()
                }
                dadaist.addAll(it)
            }
            super.onSuccess(baseBean)
        }

    }

    override fun onFail(baseBean: BaseBean) {
        super.onFail(baseBean)
    }
}