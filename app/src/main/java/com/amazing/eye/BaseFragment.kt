package com.amazing.eye

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.interfaces.IBaseView
import com.amazing.eye.viewmodel.BaseViewModel


open class BaseFragment : Fragment(), IBaseView {

    override fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T {
        return ViewModelProviders.of(this).get(viewModelClass)
    }

    override fun registerViewModelObserver(baseViewModel: BaseViewModel) {
        baseViewModel.getSuccessLiveData().observe(this, Observer<BaseBean> {
            onApiSuccessCallBack(it)
        })

        baseViewModel.getErrorLiveData().observe(this, Observer<BaseBean> {
            onApiErrorCallBack(it)
        })
    }

    override fun onApiSuccessCallBack(baseBean: BaseBean) {
    }

    override fun onApiErrorCallBack(baseBean: BaseBean) {
    }

}
