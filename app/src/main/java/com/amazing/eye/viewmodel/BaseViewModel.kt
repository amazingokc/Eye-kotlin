package com.amazing.eye.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amazing.eye.IDatasListener
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.model.BaseModel
import java.util.*

open class BaseViewModel constructor(var baseModel: BaseModel) : ViewModel(), IDatasListener {

    private val successLiveData = MutableLiveData<Any>()
    private val errorLiveData = MutableLiveData<Any>()

    fun getSuccessLiveData(): MutableLiveData<Any> = successLiveData
    fun getErrorLiveData(): MutableLiveData<Any> = errorLiveData

    open fun onSuccess(any: Any) {
        successLiveData.value = any
    }

    open fun onFail(any: Any) {
        errorLiveData.value = any
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