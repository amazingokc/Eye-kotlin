package com.amazing.eye.model

import com.amazing.eye.interfaces.IDatasListener
import com.amazing.eye.bean.BaseBean
import com.amazing.eye.network.RestCreator
import com.amazing.eye.network.RestService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseModel {

    abstract fun getObservable(): Observable<out BaseBean>

    protected fun getRestService(): RestService {
        return RestCreator.getInstance().getRestService()
    }

    fun loadData() {
        getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseBean> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(baseBean: BaseBean) {
                    baseBean.responCode = 1
                    baseBean.responType = ""
                    iDatasListener?.getSuccess(baseBean)
                }

                override fun onError(e: Throwable) {
                    var baseBean = BaseBean()
                    baseBean.responCode = -1
                    baseBean.responType = ""
                    baseBean.errorMessage = e.message.toString()
                    iDatasListener?.getfaild(baseBean)
                }

            })
    }

    private var iDatasListener: IDatasListener? = null

    fun setIDatasListener(iDatasListener: IDatasListener) {
        this.iDatasListener = iDatasListener
    }
}