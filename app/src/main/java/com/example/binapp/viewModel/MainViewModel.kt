package com.example.binapp.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.binapp.model.binData.Bank
import com.example.binapp.model.binData.BinData
import com.example.binapp.model.binData.Country
import com.example.binapp.model.binData.NumberBin
import com.example.binapp.retrofit.RetrofitClient
import com.example.binapp.retrofit.RetrofitServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(

) : ViewModel() {


}
