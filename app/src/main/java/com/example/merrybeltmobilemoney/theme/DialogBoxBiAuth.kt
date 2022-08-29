package com.example.merrybeltmobilemoney.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.merrybeltmobilemoney.R
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui.OutlinedTextFieldsTextPin

@Composable
fun DialogBoxBiAuth(
    cornerRadius: Dp = 16.dp,
    uiState : TransferState,
    uiEvent: (TransferEvent)->Unit
) {
    val contextForToast = LocalContext.current.applicationContext

    Dialog(
        onDismissRequest = {}
    ) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(cornerRadius)

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = MChild),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_verified_user_24),
                        contentDescription = "2-Step Verification",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 12.dp),
                    text = "Confirm ₦${uiState.amountToTransfer}. Payment to ${uiState.accNameToTransferTo}.- ${uiState.accNoToTransferTo} ${uiState.setBankName}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = Fonts.RobotoRegular,
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = "Enter your 4-digit Pin",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = Fonts.RobotoBold,
                        fontSize = 13.sp
                    )
                )

                OutlinedTextFieldsTextPin(
                    value = uiState.enteringPin,
                    onValueChange = {pin->
                        uiEvent(
                            TransferEvent.OnEnteringPin(pin)
                        )
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MChild),
                    onClick = {
                        uiEvent(
                            TransferEvent.OnClickOnDoneButton
                        )
                    }
                ) {
                    Text(
                        text = "Done",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = Fonts.RobotoMedium,
                            fontSize = 16.sp
                        )
                    )
                }

                TextButton(
                    onClick = {
                        uiEvent(
                            TransferEvent.OnShowAndHidePinDialog(
                                showAndHidePinDialog = false
                            )
                        )
                    }) {
                    Text(
                        text = "Cancel",
                        color = Color(0xFF35898f),
                        style = TextStyle(
                            fontFamily = Fonts.RobotoNormal,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}