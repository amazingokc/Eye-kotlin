package com.amazing.eye.viewmodel

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.MutableLiveData
import com.amazing.eye.IDatasListener
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.model.BaseModel

open class BaseViewModel constructor(var baseModel: BaseModel) : IDatasListener {

    private val successLiveData = MutableLiveData<Any>()
    private val errorLiveData = MutableLiveData<Any>()

    protected fun onSuccess(resonCode: Int, responType: String, data: BaseBean) {
        successLiveData.setValue(data)
    }

    protected fun onFail(resonCode: Int, responType: String, data: BaseBean) {
        errorLiveData.setValue(data)
    }

    open fun loadData() {
        baseModel.setIDatasListener(this)
        baseModel.loadData()
    }

    override fun getSuccess(responCode: Int, responType: String, baseBean: BaseBean) {

    }

    override fun getfaild(responCode: Int, responType: String, errorMessage: String, baseBean: BaseBean) {

    }
}