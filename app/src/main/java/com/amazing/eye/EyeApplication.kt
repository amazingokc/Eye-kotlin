package com.amazing.eye

import android.app.Application

class EyeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.initContext(this)
    }
}