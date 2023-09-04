package com.pony.compose.util

import timber.log.Timber

/**
 *Created by pony on 2022/7/7
 *Description->
 */
object AppLogger {

    private const val TAG = "AppLogger"

    @JvmStatic
    private fun tree(tag: String?): Timber.Tree {
        val tagText = if (tag.isNullOrEmpty()) TAG else tag
        return Timber.tag(tagText)
    }

    @JvmStatic
    fun d(tag: String?, message: String?) {
        tree(tag).d(message)
    }


}