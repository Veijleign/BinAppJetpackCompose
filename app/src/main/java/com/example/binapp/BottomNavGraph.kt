package com.example.binapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.binapp.model.binData.Bank
import com.example.binapp.model.binData.BinData
import com.example.binapp.model.binData.Country
import com.example.binapp.model.binData.NumberBin
import com.example.binapp.screens.bottomScreens.LatestRequestsScreen
import com.example.binapp.screens.MainScreenTest
import com.example.binapp.screens.bottomScreens.MainScreen
import com.example.binapp.viewModel.MyViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val currentData = rememberSaveable {
        mutableStateOf(
            BinData( // ???
                Bank("-", "-", "-", ""),
                "-",
                Country("", "-", "-", 0f, 0f, "-", "-"),
                NumberBin(0, false),
                false,
                "-",
                "-"
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            MainScreen(receivedData = currentData, onClickSearch = {
                getData(it, currentData)
                Log.d("TestBinNumberTag", "Value: $it")
            } )
            /*MainScreenTest(receivedData = currentData, onClickSearch = {
                getData(it, currentData)
                Log.d("TestBinNumberTag", "Value: $it")
            } )*/
        }
        composable(route = BottomBarScreen.LatestsRequests.route) {
            LatestRequestsScreen()
        }
    }
}