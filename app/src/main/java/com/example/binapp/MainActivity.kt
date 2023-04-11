package com.example.binapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.binapp.model.binData.Bank
import com.example.binapp.model.binData.BinData
import com.example.binapp.model.binData.Country
import com.example.binapp.model.binData.NumberBin
import com.example.binapp.retrofit.RetrofitClient
import com.example.binapp.retrofit.RetrofitServices
import com.example.binapp.screens.bottomScreens.MainScreen
import com.example.binapp.screens.NewMainScreen
import com.example.binapp.ui.theme.BinAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 7 main positions


        setContent {
            /*val currentData = remember {
                mutableStateOf(
                    BinData( // ???
                        Bank("", "", "", ""),
                        "",
                        Country("", "", "", 0f, 0f, "", ""),
                        NumberBin(0, false),
                        false,
                        "",
                        ""
                    )
                )
            }*/

            /*MainScreen(currentData, onClickSearch = { // c помощью it вытаскиваем данные из
                // другого класса при нажатии на кнопку
                getData(it, currentData)
                Log.d("TestBinNumberTag", "Value: $it")
            })*/

            NewMainScreen()
        }
    }
}

fun getData(
    binNumber: String,
    currentData: MutableState<BinData>
) {
    CoroutineScope(Dispatchers.IO).launch {
        val retrofitService = RetrofitClient.getClient("https://lookup.binlist.net/")
            .create(RetrofitServices::class.java)
        try {
            val cardInfo = retrofitService.getInfoByCardNumber(binNumber.toInt())
            Log.d("TestBinNumberTag", "ReceivedData: $cardInfo")
            currentData.value = cardInfo
            Log.d("TestBinNumberTag", "CurrentData: ${currentData.value}")

        } catch (e: java.lang.Exception) {
            Log.d("TestBinNumberTag", "Exception: $e")
        }
    }
}