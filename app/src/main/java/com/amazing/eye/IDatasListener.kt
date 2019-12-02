package com.amazing.eye

import com.amazing.eye.bean.BaseBean

interface IDatasListener {

    fun getSuccess(responCode: Int, responType: String, baseBean: BaseBean)

    fun getfaild(responCode: Int, responType: String, errorMessage: String, baseBean: BaseBean)
}