package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_ui


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
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_domain.BuyDataViewModel

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
                        text = "Buy Data",
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
            ) {

                BuyDataVariousNetwork(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                DataPlan(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                dataNumberInput(
                    label = "Phone Number",
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

                dataNumberInput(
                    label = "Amount",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            DataEvent.OnAmount(
                                it
                            )
                        )
                    },
                    readOnly = true
                )

                dataSubmitButton(
                    submit = {
                        uiEvent(
                            DataEvent.OnContinue,
                        )
                    },
                    label = "Continue"
                )

                if (uiState.showAndHidePinDialog) {
                    dataPinDialog(
                        uiState = uiState,
                        uiEvent = uiEvent
                    )
                }

                if (uiState.showAndHideLoader) {
                    DialogBoxLoading()
                }

                dataMessageDialogs(
                    isDialogShow = uiState.messageDialogShowAndHide,
                    isDialogMessage = uiState.messageDialogContent,
                    isDialogTitle = uiState.messageDialogTitle,
                    onDismissRequest = {
                        uiEvent(
                            DataEvent.MessageDialog(
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