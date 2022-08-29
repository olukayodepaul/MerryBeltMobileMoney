package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_presenter.TransferViewModel
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent

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
                            fontFamily = Fonts.Montserrat,
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
                modifier = Modifier
                    .background(color = MChild)
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) {

                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(color = MChild)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center

                ) {

                    Text(
                        text = "Current Balance",
                        style = TextStyle(
                            fontFamily = Fonts.RobotoBold,
                            color = White,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )

                    Text(
                        text = uiState.balances,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 29.sp,
                            fontFamily = Fonts.RobotoBold,
                            color = White
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            //.clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                            .background(White)
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Spacer(modifier = Modifier.padding(top = 40.dp))

                        Row(Modifier.height(70.dp)) {
                            Box(
                                modifier = Modifier
                                    .width(55.dp)
                                    .height(64.dp)
                                    .padding(top = 8.dp, end = 5.dp)
                                    .border(width = 1.dp, color = MaterialBg)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .border(
                                        BorderStroke(
                                            width = 1.dp, color = Borderline
                                        )
                                    ),
                                contentAlignment = Alignment.Center

                            ) {
                                if(uiState.selectedBankLogo.isNotEmpty()){
                                    val leadingIcon =
                                        rememberImagePainter(data = uiState.selectedBankLogo,
                                            builder = {
                                            }
                                        )
                                    Image(
                                        painter = leadingIcon,
                                        contentDescription = "logo",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                            .padding(7.dp),
                                    )
                                    val painterState = leadingIcon.state
                                    if (painterState is ImagePainter.State.Loading) {
                                        CircularProgressIndicator(
                                            color = MChild
                                        )
                                    }
                                }
                            }

                            BankList(
                                uiState = uiState,
                                uiEvent = uiEvent,
                            )
                        }

                        OutlinedTextFieldsNumber(
                            label = "Account Number",
                            value = uiState.accNoToTransferTo,
                            onValueChange = {accNoToTransferTo->
                                uiEvent(
                                    TransferEvent.OnAccNoToTransferTo(accNoToTransferTo)
                                )
                            },
                            enabled = true,
                        )

                        Spacer(modifier = Modifier.padding(bottom = 5.dp))

                        OutlinedTextFieldsText(
                            readOnly = true,
                            label = "Account Name",
                            value = uiState.accNameToTransferTo,
                            onValueChange = {},
                            enabled = true
                        )

                        Spacer(modifier = Modifier.padding(bottom = 5.dp))

                        OutlinedTextFieldsNumber(
                            label = "Amount",
                            value = uiState.amountToTransfer,
                            onValueChange = {amountToTransfer->
                                uiEvent(
                                    TransferEvent.OnAmountToTransfer(amountToTransfer)
                                )
                            },
                            enabled = true
                        )

                        Spacer(modifier = Modifier.padding(bottom = 5.dp))

                        Buttons(
                            label = "Continue",
                            uiEvent = uiEvent,
                            uiState = uiState
                        )

                        if(uiState.loader){
                            DialogBoxLoading()
                        }

                        if(uiState.showAndHidePinDialog) {
                            DialogBoxBiAuth(
                                uiState = uiState,
                                uiEvent = uiEvent
                            )
                        }

                        Spacer(modifier = Modifier.padding(bottom = 30.dp))
                    }
                }
            }
        }
    )
}