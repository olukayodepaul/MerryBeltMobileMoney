package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component.AuthenticationErrorDialogs
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_presenter.TransferViewModel


@Composable
fun transferComposableActivity(
    transViewModel: TransferViewModel = hiltViewModel(),
) {
    val uiState = transViewModel.uiState.collectAsState().value
    val uiEvent = transViewModel::transEventHandler

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Transfer Fund",
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

                spanceWidget(label ="Banks")
                bankList(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                spanceWidget(label ="Account Number")
                transInput(
                    label = "",
                    value = uiState.accountNumber,
                    onValueChange = {
                        uiEvent(
                            TransferEvent.OnAccountNumber(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                spanceWidget(label ="Account Name")
                transInput(
                    label = "",
                    value = uiState.accountName,
                    onValueChange = {
                    },
                    readOnly = false
                )

                spanceWidget(label ="Amount")
                transInput(
                    label = "",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            TransferEvent.OnAmount(
                                it
                            )
                        )
                    },
                    readOnly = false
                )

                if(uiState.bankLoader) {
                    DialogBoxLoading()
                }

                transSubmitButton(
                    label = "Continue",
                    submit = {
                        uiEvent(
                            TransferEvent.OnContinue
                        )
                    }
                )

                AuthenticationErrorDialogs(
                    isDialogShow = uiState.msgVisibility,
                    isDialogMessage = uiState.msg,
                    isDialogTitle = uiState.msgTitle,
                    onDismissRequest = {
                        uiEvent(
                            TransferEvent.OnMessageDialog(
                                msgTitle = "",
                                msgVisibility = false,
                                msg = ""
                            )
                        )
                    }
                )

                if(uiState.hideAnsShowPinDialog){
                    TransferPinDialog(
                        uiState = uiState,
                        uiEvent =  uiEvent
                    )
                }

                FullScreenDialog(
                    uiState = uiState
                )
            }
        }
    )

}





