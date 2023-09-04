package com.pony.compose.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.2"

    object Google {
        const val material = "com.google.android.material:material:1.5.0"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Compose {
            const val compilerVersion = "1.2.0"
            private const val version = "1.2.0"

            const val ui = "androidx.compose.ui:ui:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val livedata = "androidx.compose.runtime:runtime-livedata:$version"

            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha14"
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Paging {
            private const val paging_version = "3.1.1"
            const val paging_runtime = "androidx.paging:paging-runtime:$paging_version"
            const val paging_common = "androidx.paging:paging-common:$paging_version"
            const val paging_compose = "androidx.paging:paging-compose:1.0.0-alpha15"
        }
    }

    object Accompanist {
        private const val version = "0.26.1-alpha"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val permission = "com.google.accompanist:accompanist-permissions:$version"
        const val webView = "com.google.accompanist:accompanist-webview:$version"
        const val flowlayout = "com.google.accompanist:accompanist-flowlayout:$version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val navigation = "com.google.accompanist:accompanist-navigation-animation:$version"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
    }

    object Hilt {
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    }

    object Coli {
        const val coliCompose = "io.coil-kt:coil-compose:2.0.0"
    }

    object Libraries {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object OkHttp {
        //Retrofit2
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        //okhttp
        private const val okhttp_version = "4.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    }
}