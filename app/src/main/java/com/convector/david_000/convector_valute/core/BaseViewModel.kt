package com.convector.david_000.convector_valute.core

package ru.beeline.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

//    protected fun safeLaunch(
//        // onFailure: suspend (errorData: ErrorData) -> Unit = {},
//        block: suspend CoroutineScope.() -> Unit
//    ): Job {
//        return viewModelScope.launch {
//            supervisorScope {
//                try {
//                    block.invoke(this@launch)
//                } catch (th: Throwable) {
//                    Timber.e(th.printStackTrace().toString(), th)
////                    onFailure.invoke(errorHandler.handle(th))
//                }
//            }
//        }
//    }

    protected fun safeLaunch(
        coroutineContext: CoroutineContext = Dispatchers.IO,
        onFailure: FailureListener = {},
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineContext) {
            supervisorScope {
                try {
                    block.invoke(this@launch)
                } catch (th: Throwable) {
                    Timber.e(th.printStackTrace().toString())
                    onFailure.invoke(th)
                }
            }
        }
    }
}
