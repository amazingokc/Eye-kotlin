package com.amazing.eye.model

import com.amazing.eye.bean.BaseBean
import io.reactivex.Observable

class HotModel : BaseModel() {

    var strategy: String = ""

    override fun getObservable(): Observable<out BaseBean> {
        return getRestService().getHotData(
            10,
            strategy,
            "26868b32e808498db32fd51fb422d00175e179df",
            83
        )
    }
}