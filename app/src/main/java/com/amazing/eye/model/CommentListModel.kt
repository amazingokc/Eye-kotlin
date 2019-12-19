package com.amazing.eye.model

import com.amazing.eye.bean.BaseBean
import io.reactivex.Observable

class CommentListModel : BaseModel() {

    var videoId:Int = 0
    var lastId:String = ""

    override fun getObservable(): Observable<out BaseBean> {
        return getRestService().getCommentListData(10, videoId,lastId)
    }
}