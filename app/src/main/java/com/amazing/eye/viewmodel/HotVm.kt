package com.amazing.eye.viewmodel

import com.amazing.eye.bean.HotBean
import com.amazing.eye.model.HotModel

class HotVm : BaseViewModel() {

    var dataList = mutableListOf<HotBean.ItemListBean.DataBean>()

    fun getDatas(): MutableList<HotBean.ItemListBean.DataBean> {
        return dataList
    }

    fun loadData(strategy: String) {
        if (baseModel == null) {
            baseModel = HotModel()
        }
        (baseModel as HotModel).strategy = strategy
        super.loadData()
    }

    override fun onSuccess(any: Any) {
        if (any is HotBean) {
            any.itemList?.forEach{
                it.data?.let { it1 ->
                    dataList.add(it1)
                }
            }
            super.onSuccess(any)
        }
    }
}