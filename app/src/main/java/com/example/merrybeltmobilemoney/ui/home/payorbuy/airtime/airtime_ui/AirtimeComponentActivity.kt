package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_domain.AirtimeViewModel


@Composable
fun AirtimeComponentActivity(
    transViewModel: AirtimeViewModel = hiltViewModel(),
) {

    val uiState = transViewModel.uiState.collectAsState().value
    val uiEvent = transViewModel::airtimeEventHandler

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "AIRTIME",
                        style = TextStyle(
                            fontFamily = Fonts.RobotoMedium,
                            fontSize = 20.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = White)
                    }
                },
                backgroundColor = MChild,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }, content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                spanceWidget(label ="Network")
                AirtimeProductLis(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label ="Phone Number")
                airtimeInput(
                    label = "",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            AirtimeEvent.OnPhoneNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label ="Amount")
                airtimeInput(
                    label = "",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            AirtimeEvent.OnAmount(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                airtimeSubmitButton(
                    label = "Continue"
                )

            }
        }
    )

}