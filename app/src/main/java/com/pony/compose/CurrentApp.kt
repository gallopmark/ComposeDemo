package com.pony.compose

import android.app.Application
import timber.log.Timber

/**
 *Created by pony on 2022/7/20
 *Description->
 */
class CurrentApp :Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}