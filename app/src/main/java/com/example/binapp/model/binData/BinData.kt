package com.example.binapp.model.binData

data class BinData(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: NumberBin,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)