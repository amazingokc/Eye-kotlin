package com.amazing.eye

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import com.amazing.eye.interfaces.IBaseView
import com.amazing.eye.viewmodel.BaseViewModel


open class BaseFragment : Fragment(), IBaseView {

    override fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T {
        return ViewModelProviders.of(this).get(viewModelClass)
    }

    override fun registerViewModelObserver(baseViewModel: BaseViewModel) {
        baseViewModel.getSuccessLiveData().observe(this, Observer<Any> {
            if (it != null) {
                onApiSuccessCallBack(it)
            }
        })

        baseViewModel.getErrorLiveData().observe(this, Observer<Any> {
            if (it != null) {
                onApiErrorCallBack(it)
            }
        })
    }

    override fun onApiSuccessCallBack(any: Any) {
    }

    override fun onApiErrorCallBack(any: Any) {
    }

}
