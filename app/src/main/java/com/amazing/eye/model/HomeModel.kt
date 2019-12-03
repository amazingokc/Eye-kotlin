package com.amazing.eye.model

import com.amazing.eye.bean.HomeBean
import io.reactivex.Observable

class HomeModel() : BaseModel() {

    override fun getObservable(): Observable<HomeBean> = getRestService().getHomeData()

}