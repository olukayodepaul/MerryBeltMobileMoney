package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_ui


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
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_domain.BuyDataViewModel
import dataInput
import dataPlanList
import dataProductLis
import dataSubmitButton

@Composable
fun DataPurchaseComponentActivity(
    dataViewModel: BuyDataViewModel = hiltViewModel(),
) {

    val uiState = dataViewModel.uiState.collectAsState().value
    val uiEvent = dataViewModel::dataEventHandler

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DATA",
                        style = TextStyle(
                            fontFamily = Fonts.RobotoBold,
                            fontSize = 14.sp
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

                spanceWidget(label = "Network")
                dataProductLis(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label = "Data Plan")
                dataPlanList(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label = "Phone Number")
                dataInput(
                    label = "",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            DataEvent.OnPhoneNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label = "Amount")
                dataInput(
                    label = "",
                    value = uiState.amount,
                    onValueChange = {

                    },
                    readOnly = true
                )

                dataSubmitButton(
                    label = "Continue"
                )
            }
        }
    )
}