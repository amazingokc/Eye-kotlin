package com.amazing.eye.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amazing.eye.IDatasListener
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.model.BaseModel

open class BaseViewModel constructor(var baseModel: BaseModel) : ViewModel(), IDatasListener {

    private val successLiveData = MutableLiveData<BaseBean>()
    private val errorLiveData = MutableLiveData<BaseBean>()

    fun getSuccessLiveData(): MutableLiveData<BaseBean> = successLiveData
    fun getErrorLiveData(): MutableLiveData<BaseBean> = errorLiveData

    open fun onSuccess(baseBean: BaseBean) {
        successLiveData.value = baseBean
    }

    open fun onFail(baseBean: BaseBean) {
        errorLiveData.value = baseBean
    }

    open fun loadData() {
        baseModel.setIDatasListener(this)
        baseModel.loadData()
    }

    override fun getSuccess(baseBean: BaseBean) {
        onSuccess(baseBean)
    }

    override fun getfaild(baseBean: BaseBean) {
        onFail(baseBean)
    }
}