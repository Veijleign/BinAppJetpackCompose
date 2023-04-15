package com.example.binapp.model.binData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Float,
    val longitude: Float,
    val name: String,
    val numeric: String
): Parcelable