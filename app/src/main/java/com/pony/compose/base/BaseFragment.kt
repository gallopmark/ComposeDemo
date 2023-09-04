package com.pony.compose.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

/**
 *Created by pony on 2022/7/4
 *Description->
 */
abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(mContext).apply {
            setContent {
                CreateContentView()
            }
        }
    }

    @Composable
    abstract fun CreateContentView()
}