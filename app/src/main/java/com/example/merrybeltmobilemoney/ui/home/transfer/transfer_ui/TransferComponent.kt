package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.R
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.theme.Fonts.MontserratBold
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoBold
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoMedium
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoNormal
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoRegular
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState


@Composable
fun bankList(
    uiState: TransferState,
    uiEvent: (TransferEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.bankSelected.uppercase(), //Menu Active Text
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
            readOnly = true,
            shape = RoundedCornerShape(6.dp),
//            leadingIcon = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        painter = painterResource(id = cusIcon(1)),
//                        contentDescription = ""
//                    )
//                }
//            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(
                    bColor.red, bColor.green, bColor.blue,
                    TextFieldDefaults.BackgroundOpacity
                ),
                focusedBorderColor = bColor,
                unfocusedBorderColor = Color(
                    bColor.red, bColor.green, bColor.blue,
                    TextFieldDefaults.UnfocusedIndicatorLineOpacity,
                ),
                focusedLabelColor = GreyTransparent,
                cursorColor = GreyTransparent
            ),
        )
        DropdownMenu(
            expanded = uiState.bankExpanded,
            onDismissRequest = {
                uiEvent(
                    TransferEvent.OnBankExpanded(
                        bankExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.bankList.forEachIndexed { index, specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        TransferEvent.OnBankExpanded(
                            bankExpanded = false
                        )
                    )

                    uiEvent(
                        TransferEvent.OnBankSelected(
                            bankSelected = specimen.name,
                            bankImage = specimen.url!!,
                            backCode = specimen.code
                        )
                    )


                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Column(
                            Modifier
                                .padding(end = 10.dp)
                                .width(20.dp)
                                .height(20.dp)
                        ) {

                            val painter = rememberImagePainter(
                                data = specimen.url,
                                builder = {})

                            Image(
                                painter = painter,
                                contentDescription = "Images",
                            )

                            val painterState = painter.state
                            if (painterState is ImagePainter.State.Loading) {
                                CircularProgressIndicator(
                                    color = MChild
                                )
                            }
                        }
                        Text(
                            text = specimen.name.uppercase(),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = Fonts.RobotoBold,
                                fontWeight = FontWeight.W600
                            )
                        ) //Dropdownmenu list items
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = {
                        uiEvent(
                            TransferEvent.OnBankExpanded(
                                bankExpanded = !uiState.bankExpanded
                            )
                        )
                    }
                )
        )
    }
}

@Composable
fun transInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(MaterialBg)
            .focusable(enabled = true),
        value = value,
        onValueChange = {
            onValueChange(it)
        },

        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        readOnly = readOnly,
        maxLines = 1,
        shape = RoundedCornerShape(6.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(
                bColor.red, bColor.green, bColor.blue,
                TextFieldDefaults.BackgroundOpacity
            ),
            focusedBorderColor = bColor,
            unfocusedBorderColor = Color(
                bColor.red, bColor.green, bColor.blue,
                TextFieldDefaults.UnfocusedIndicatorLineOpacity,
            ),
            focusedLabelColor = GreyTransparent,
            cursorColor = GreyTransparent,
        ),
    )
}



@Composable
fun transSubmitButton(
    submit: () -> Unit,
    label: String,
) {
    Button(
        onClick = {
            submit()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MChild
        ),

        ) {
        Text(
            text = label,
            style = TextStyle(
                color = White,
                fontSize = 20.sp,
                fontFamily = Fonts.MontserratBold
            ),
        )
    }
}


@Composable
fun TransferPinDialog(
    uiState: TransferState,
    uiEvent:(TransferEvent)->Unit
) {
    val contextForToast = LocalContext.current.applicationContext

    Dialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.padding(top = 16.dp),
                        painter = painterResource(id = R.drawable.ic_security_error),
                        contentDescription = "",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    text = "Verification",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = RobotoMedium,
                        fontSize = 20.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 5.dp),
                    text = "Confirm ₦${uiState.amount} payment to ${uiState.accountName.uppercase()} - ${uiState.accountNumber} - ${uiState.bankSelected}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = RobotoMedium,
                        fontSize = 15.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 5.dp, top = 5.dp),
                    text = "Enter your 4-digit Pin",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = RobotoBold,
                        fontSize = 14.sp
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( start = 36.dp, end = 36.dp),
                ) {
                    transInput(
                        label = "",
                        value = uiState.bankPin,
                        onValueChange = {
                            uiEvent(
                                TransferEvent.OnBankPin(
                                    it
                                )
                            )
                        },
                        readOnly = false
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MChild),
                    onClick = {
                        uiEvent(
                            TransferEvent.OnTransfer
                        )
                    }) {
                    Text(
                        text = "Transfer",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = RobotoMedium,
                            fontSize = 16.sp
                        )
                    )
                }

                TextButton(
                    onClick = {
                        uiEvent(
                            TransferEvent.OnHideAnsShowPinDialog(
                                hideAnsShowPinDialog = false
                            )
                        )
                    }) {
                    Text(
                        text = "Cancel",
                        color = BgBlack,
                        style = TextStyle(
                            fontFamily = RobotoMedium,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullScreenDialog(
    primaryColor: Color = MChild,
    uiState: TransferState
) {

    if (uiState.successHideAndShowDialog) {

        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false // experimental
            )
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // medal icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_check_circle_24),
                        contentDescription = "Medal icon",
                        tint = primaryColor,
                        modifier = Modifier.size(size = 120.dp)
                    )

                    Text(
                        text = "Transfer Successfully",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 26.dp),
                        fontFamily = RobotoMedium
                    )

                    Text(
                        text = "${uiState.accountName.uppercase()}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 20.dp),
                        fontFamily = RobotoMedium,
                        color = Monsoon
                    )

                    Text(
                        text = "${uiState.accountNumber} - ${uiState.bankSelected}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        fontFamily = RobotoMedium,
                        color = Monsoon
                    )

                    Text(
                        text = "₦${uiState.amount.toDouble()}",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 20.dp),
                        fontFamily = RobotoBold
                    )

                    Button(
                        onClick = {
                            //redirect to home page
                        },
                        modifier = Modifier
                            .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(percent = 15)
                    ) {
                        Text(
                            text = "Done",
                            fontFamily = RobotoMedium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

// text = " ₦${uiState.amount} payment to ",