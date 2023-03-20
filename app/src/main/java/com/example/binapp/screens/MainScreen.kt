package com.example.binapp.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.binapp.model.binData.BinData
import com.example.binapp.ui.theme.Purple200


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

    val testIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(receivedData.value.bank.url)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                            Text(text = if (receivedData.value.number.luhn) {
                                "Yes"
                            } else {
                                "No"
                            })
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
                    Text(text = if (receivedData.value.number.luhn) {
                        "Yes"
                    } else {
                        "No"
                    })
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
                            val coordinates: String ="geo:" + receivedData.value.country.latitude + "," + receivedData.value.country.longitude
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coordinates))
                            startActivity(context, intent, null)
                        }
                    ) {
                        Row(

                        ) {
                            Text(text = "(latitude: ",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(text = "${receivedData.value.country.latitude}",
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                            Text(text = ",",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                        }
                        Row(

                        ) {
                            Text(text = "longitude: ",
                                fontSize = 11.sp,
                                color = Color.Black.copy(alpha = 0.3f)
                            )
                            Text(text = "${receivedData.value.country.longitude}",
                                fontSize = 11.sp,
                                color = Color.Black
                            )
                            Text(text = ")",
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
                Text(text = "${receivedData.value.bank.name}, ${receivedData.value.bank.city}")
                Text(text = receivedData.value.bank.phone, modifier = Modifier.clickable {
                    val phoneNumber: String ="tel:" + receivedData.value.bank.phone
                    val newString = phoneNumber.substringBefore(" OR")
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(newString))
                    startActivity(context, intent, null)
                })

                Text(text = receivedData.value.bank.url, Modifier.clickable {
                    val urlAddress: String = "https://" + receivedData.value.bank.url
                    try {
                        // todo переделать нормальный переход в браузер
                        CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(urlAddress))
                        uriHandler.openUri(urlAddress)


                    } catch (e: Exception) {
                        Log.d("BinNumberTag", "Exception: $e")
                    }
                })
            }
        }
        TextField(
            modifier = Modifier
                .padding(top = 15.dp)
                .border(width = 1.dp, color = Purple200, shape = RoundedCornerShape(10.dp)),
            value = searchBIN.value,
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
        Button(onClick = {
            onClickSearch(searchBIN.value) // check difference: .invoke(searchBIN.value)
        }) {
            Text(text = "Find BIN")
        }
    }
    Log.d("BINNumber", searchBIN.value) // сделать чтобы изменялся только один раз
}

