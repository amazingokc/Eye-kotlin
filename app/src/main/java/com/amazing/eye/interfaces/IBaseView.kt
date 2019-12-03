package com.amazing.eye.interfaces

import com.amazing.eye.bean.BaseBean
import com.amazing.eye.viewmodel.BaseViewModel

interface IBaseView {

    fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T

    fun registerViewModelObserver(baseViewModel: BaseViewModel)

    fun onApiSuccessCallBack(baseBean: BaseBean)

    fun onApiErrorCallBack(baseBean: BaseBean)
}