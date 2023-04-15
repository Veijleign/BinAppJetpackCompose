package com.example.binapp.screens.bottomScreens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.binapp.model.binData.Bank
import com.example.binapp.model.binData.BinData
import com.example.binapp.model.binData.Country
import com.example.binapp.model.binData.NumberBin
import com.example.binapp.ui.theme.Purple200
import com.example.binapp.ui.theme.Teal200
import com.example.binapp.ui.theme.TealX
import com.example.binapp.viewModel.MyViewModel

@Composable
fun MainScreen(
    receivedData: MutableState<BinData>, // присвоить отсюда значения в нужные метса
    onClickSearch: (String) -> Unit
) {
    // add an inputText field only numbers
    Log.d("TestBinNumberTag", "ReceivedData: ${receivedData.value}")
    val searchBIN = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    //val testIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(receivedData.value.bank.url)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Teal200)
            .padding(start = 3.dp, end = 3.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "SCHEME / NETWORK", color = Color.Black.copy(alpha = 0.3f))
                    Text(text = receivedData.value.scheme)
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "BRAND", color = Color.Black.copy(alpha = 0.3f))
                    Text(text = receivedData.value.brand)
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "CARD NUMBER", color = Color.Black.copy(alpha = 0.3f))
                    Row(

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(end = 10.dp)
                        ) {
                            Text(
                                text = "LENGTH",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(text = receivedData.value.number.length.toString())
                        }
                        Column(

                        ) {
                            Text(
                                text = "LUHN",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(
                                text = if (receivedData.value.number.luhn) {
                                    "Yes"
                                } else {
                                    "No"
                                }
                            )
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "TYPE", color = Color.Black.copy(alpha = 0.3f))
                    Text(text = receivedData.value.type)
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "PREPAID", color = Color.Black.copy(alpha = 0.3f))
                    Text(
                        text = if (receivedData.value.number.luhn) {
                            "Yes"
                        } else {
                            "No"
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "COUNTRY", color = Color.Black.copy(alpha = 0.3f))
                    Text(text = "${receivedData.value.country.alpha2} ${receivedData.value.country.name}")

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val coordinates: String =
                                "geo:" + receivedData.value.country.latitude + "," + receivedData.value.country.longitude
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coordinates))
                            ContextCompat.startActivity(context, intent, null)
                        }
                    ) {
                        Row(

                        ) {
                            Text(
                                text = "(latitude: ",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(
                                text = "${receivedData.value.country.latitude}",
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                            Text(
                                text = ",",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                        }
                        Row(

                        ) {
                            Text(
                                text = "longitude: ",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(
                                text = "${receivedData.value.country.longitude}",
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                            Text(
                                text = ")",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                        }

                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
                // verticalArrangement = Arrangement.Center // can be deleted or added, doesn't matter
            ) {
                Text(text = "BANK", color = Color.Black.copy(alpha = 0.3f))
                Text(text = "${receivedData.value.bank.name} ${receivedData.value.bank.city}")
                Text(text = receivedData.value.bank.phone, modifier = Modifier.clickable {
                    val phoneNumber: String = "tel:" + receivedData.value.bank.phone
                    val newString = phoneNumber.substringBefore(" OR")
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(newString))
                    ContextCompat.startActivity(context, intent, null)
                })

                Text(text = receivedData.value.bank.url, Modifier.clickable {
                    val urlAddress: String = "https://" + receivedData.value.bank.url
                    try {
                        // todo переделать нормальный переход в браузер и вылет при неполученных данных
                        CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(urlAddress))
                        uriHandler.openUri(urlAddress)


                    } catch (e: Exception) {
                        Log.d("BinNumberTag", "Exception: $e")
                    }
                })
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 15.dp),
            value = searchBIN.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.Black
            ),
            onValueChange = {
                searchBIN.value = it
            },
            label = {
                Text(text = "Find BIN", color = Color.Black)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onClickSearch(searchBIN.value)
                },
            )
        )
        OutlinedButton(
            onClick = {
                onClickSearch(searchBIN.value) // check difference: .invoke(searchBIN.value)
            },
            border = BorderStroke(1.dp, Color.DarkGray),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = TealX)
        ) {
            Text(
                text = "Find BIN",
                color = Color.Black
            )
        }
    }
    Log.d("BINNumber", searchBIN.value) // сделать чтобы изменялся только один раз
}