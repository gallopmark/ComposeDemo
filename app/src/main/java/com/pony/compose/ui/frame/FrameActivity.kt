package com.pony.compose.ui.frame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import com.pony.compose.base.BaseActivity
import java.lang.ref.WeakReference

/**
 *Created by pony on 2022/7/14
 *Description->
 */
class FrameActivity : BaseActivity() {

    companion object {
        private const val FRAME_CONTAINER_ID = 0x123123
        const val KEY_CLASS = "fragment_class_name"
        const val KEY_TITLE = "frame_title"
        const val KEY_SHOW_TITLE_BAR = "frame_show_title_bar"
        private const val FRAGMENT_TAG = "content_fragment_tag"

        fun navigate(context: Context, canonicalName: String?, title: String? = "", showTitleBar: Boolean = true) {
            val intent = Intent(context, FrameActivity::class.java).apply {
                putExtra(KEY_CLASS, canonicalName)
                putExtra(KEY_TITLE, title)
                putExtra(KEY_SHOW_TITLE_BAR, showTitleBar)
            }
            context.startActivity(intent)
        }
    }

    private var mFragmentCache: WeakReference<Fragment>? = null

    override fun requireToolbar(): Boolean = intent.getBooleanExtra(KEY_SHOW_TITLE_BAR, true)

    override fun requireTitle(): String = intent.getStringExtra(KEY_TITLE) ?: super.requireTitle()

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = {
                FrameLayout(it).apply { id = FRAME_CONTAINER_ID }
            }, modifier = Modifier.fillMaxSize(), update = { container ->
                var fragment: Fragment? = null
                savedInstanceState?.let {
                    fragment = supportFragmentManager.getFragment(it, FRAGMENT_TAG)
                }
                if (fragment == null) {
                    fragment = getFromIntent()
                }
                fragment?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(container.id, it)
                        .commitAllowingStateLoss()
                    mFragmentCache = WeakReference(it)
                }
            })
        }
    }

    private fun getFromIntent(): Fragment? {
        val fragmentName: String? = intent.getStringExtra(KEY_CLASS)
        if (fragmentName.isNullOrEmpty()) return null
        return try {
            val clz = Class.forName(fragmentName)
            val fragment = clz.newInstance() as Fragment
            fragment.arguments = intent.extras
            fragment
        } catch (e: Exception) {
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mFragmentCache?.get()?.let {
            supportFragmentManager.putFragment(outState, FRAGMENT_TAG, it)
        }
    }
}