package com.amazing.eye

import com.amazing.eye.bean.BaseBean

interface IDatasListener {

    fun getSuccess(baseBean: BaseBean)

    fun getfaild(baseBean: BaseBean)
}