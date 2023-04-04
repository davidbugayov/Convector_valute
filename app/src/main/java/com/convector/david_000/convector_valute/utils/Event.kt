package com.convector.david_000.convector_valute.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class Event<out T>(private val content: T) {
    var isHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

fun <T> Flow<Event<T>>.onEachEvent(action: suspend (T) -> Unit): Flow<Event<T>> {
    return onEach { event ->
        event.getContentIfNotHandled()?.let {
            action.invoke(it)
        }
    }
}
