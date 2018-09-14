package com.panritech.fuad.footballmatchapp.provider

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}