package com.panritech.fuad.footballmatchapp.provider

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}