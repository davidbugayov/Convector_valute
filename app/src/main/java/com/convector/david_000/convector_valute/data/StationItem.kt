package com.convector.david_000.convector_valute.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StationItem(
    val name: String,
    val code: Long
) : Parcelable