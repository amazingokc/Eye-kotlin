package com.amazing.eye.viewmodel

import android.widget.ImageView
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.bean.HomeBean
import com.amazing.eye.model.HomeModel

class HomeVm : BaseViewModel(HomeModel()) {

    var dadaist = mutableListOf<HomeBean.IssueListBean.ItemListBean>()

    var isLoadMore = false

    fun getDatas(): MutableList<HomeBean.IssueListBean.ItemListBean> {
        return dadaist
    }

    fun loadData(isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore
        loadData()
    }


    override fun onSuccess(any: Any) {
        if (any is HomeBean) {
            any.issueList?.get(0)?.itemList?.let {
                if (!isLoadMore) {
                    dadaist.clear()
                }
                for (i in it) {
                    if ("video" == i.type) {
                        dadaist.add(i)
                    }
                }
            }
            super.onSuccess(dadaist)
        }
         var imageView :ImageView
    }

    override fun onFail(any: Any) {
        if (any is HomeBean) {
            super.onFail(any.errorMessage)
        }
    }
}