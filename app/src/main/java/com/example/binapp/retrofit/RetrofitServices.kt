package com.example.binapp.retrofit

import com.example.binapp.model.binData.BinData
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("{cardNumber}") // @Path нужен для уточнения куда вставлять данные, то есть в @GET
    suspend fun getInfoByCardNumber(@Path("cardNumber") cardNumber: Int): BinData

}