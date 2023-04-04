package com.convector.david_000.convector_valute.utils

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> EventSharedFlow(
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) = MutableSharedFlow<Event<T>>(replay, extraBufferCapacity, onBufferOverflow)

suspend fun <T> MutableSharedFlow<Event<T>>.emitEvent(value: T) = emit(Event(value))
