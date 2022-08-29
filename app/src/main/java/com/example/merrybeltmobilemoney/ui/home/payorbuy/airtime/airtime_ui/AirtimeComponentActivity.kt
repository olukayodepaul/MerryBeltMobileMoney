package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.merrybeltmobilemoney.theme.DialogBoxLoading
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
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
                        text = "Buy Airtime",
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

            Column(Modifier.fillMaxSize().padding(20.dp)) {

                VariousNetwork(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                numberInput(
                    label = "Phone Number",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            AirtimeEvent.OnPhoneNumber(
                                it
                            )
                        )
                    }
                )

                numberInput(
                    label = "Amount",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            AirtimeEvent.OnAmount(
                                it
                            )
                        )
                    }
                )

                airtimeSubmitButton(
                    submit = {
                        uiEvent(
                            AirtimeEvent.OnContinue,
                        )
                    },
                    label = "Continue"
                )

                if(uiState.showAndHidePinDialog) {
                    airtimePinDialog(
                        uiState = uiState,
                        uiEvent = uiEvent
                    )
                }

                if(uiState.showAndHideLoader) {
                    DialogBoxLoading()
                }

                airtimeMessageDialogs(
                    isDialogShow = uiState.messageDialogShowAndHide,
                    isDialogMessage = uiState.messageDialogContent,
                    isDialogTitle = uiState.messageDialogTitle,
                    onDismissRequest = {
                        uiEvent(
                            AirtimeEvent.MessageDialog(
                                message = "",
                                viewStatus = false
                            )
                        )
                    }
                )
            }
        }
    )

}