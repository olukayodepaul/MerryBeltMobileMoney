package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_ui

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
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.theme.spanceWidget
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_domain.PhcnViewModel


@Composable
fun PhcnComponentActivity(
    dataViewModel: PhcnViewModel = hiltViewModel(),
) {

    val uiState = dataViewModel.uiState.collectAsState().value
    val uiEvent = dataViewModel::phcnEventHandler

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "PHCN",
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

                spanceWidget(label = "Category")
                PhcnProductLis(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label = "Meter Type")
                MeterTypeList(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label ="Meter Number")
                phcnInput(
                    label = "",
                    value = uiState.meterNumber,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnMeterNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label ="Phone Number")
                phcnInput(
                    label = "",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnPhoneNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label ="Amount")
                phcnInput(
                    label = "",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnAmount(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                phcnSubmitButton(
                    label = "Continue"
                )
            }
        }
    )

}
