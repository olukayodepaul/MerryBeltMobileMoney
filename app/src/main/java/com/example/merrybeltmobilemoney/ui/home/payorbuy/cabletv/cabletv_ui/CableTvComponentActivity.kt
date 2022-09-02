package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_ui


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
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_domain.CableTvViewModel


@Composable
fun CableTvComponentActivity(
    dataViewModel: CableTvViewModel = hiltViewModel(),
) {

    val uiState = dataViewModel.uiState.collectAsState().value
    val uiEvent = dataViewModel::cableTvEventHandler

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cable Tv",
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

                spanceWidget(label ="Category")
                CableTvProductLis(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label ="Bouquet")
                CableTvProductLisPlan (
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label ="Smart Card Number")
                cableTvInput(
                    label = "",
                    value = uiState.smartCardNumber,
                    onValueChange = {
                        uiEvent(
                            CableTvEvent.OnSmartCardNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label = "Phone Number")
                cableTvInput(
                    label = "",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            CableTvEvent.OnPhoneNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label = "Amount")
                cableTvInput(
                    label = "",
                    value = uiState.cableTvProductPlanPrice,
                    onValueChange = {},
                    readOnly = true
                )

                cableTvSubmitButton(
                    label = "Continue"
                )
            }
        }
    )
}