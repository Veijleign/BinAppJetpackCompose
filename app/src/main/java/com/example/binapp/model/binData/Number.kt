package com.example.binapp.model.binData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberBin(
    val length: Int,
    val luhn: Boolean
): Parcelable