package com.example.binapp.model.binData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinData(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: NumberBin,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
): Parcelable