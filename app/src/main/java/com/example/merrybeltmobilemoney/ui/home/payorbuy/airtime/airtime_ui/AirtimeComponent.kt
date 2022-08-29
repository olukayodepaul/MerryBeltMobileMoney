package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_ui

import android.util.Log
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
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.R
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeState
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui.OutlinedTextFieldsTextPin


@Composable
fun VariousNetwork(
    uiState: AirtimeState,
    uiEvent: (AirtimeEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.selectedNetwork, //Menu Active Text
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Mobile Network",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = Fonts.RobotoBold,
                        fontWeight = FontWeight.W600
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
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
            expanded = uiState.expandNetworkWidget,
            onDismissRequest = {
                uiEvent(
                    AirtimeEvent.OnExpandNetworkWidget(
                        expanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.difAirTimeNetwork.forEach { specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        AirtimeEvent.OnExpandNetworkWidget(
                            expanded = false
                        )
                    )

                    uiEvent(
                        AirtimeEvent.OnselectedNetwork(
                            selectedNetwork = specimen.category!!
                        )
                    )

                    uiEvent(
                        AirtimeEvent.OnselectNetworkImage(
                            selectNetworkImage = specimen.imageUrl!!
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
                                data = specimen.imageUrl,
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
                            text = specimen.category!!,
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
                            AirtimeEvent.OnExpandNetworkWidget(
                                expanded = !uiState.expandNetworkWidget
                            )
                        )
                    }
                )
        )
    }
}

@Composable
fun numberInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
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
        placeholder = {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Fonts.RobotoBold,
                    color = Blues,
                    fontSize = 18.sp
                )
            )
        },
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
fun airtimeSubmitButton(
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
fun airtimePinDialog(
    cornerRadius: Dp = 16.dp,
    uiState : AirtimeState,
    uiEvent: (AirtimeEvent)->Unit
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
                        contentDescription = "Review Payment",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 12.dp),
                    text = "Confirm the airtime of ₦${uiState.amount} send to ${uiState.phoneNumber}",
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
                    value = uiState.pin,
                    onValueChange = {pin->
                        uiEvent(
                            AirtimeEvent.OnPin(pin)
                        )
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MChild),
                    onClick = {
//                        uiEvent(
//                            TransferEvent.OnClickOnDoneButton
//                        )
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
                            AirtimeEvent.OnshowAndHidePinDialog(
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


@Composable
fun airtimeMessageDialogs (
    isDialogShow : Boolean,
    isDialogMessage: String,
    isDialogTitle: String = "",
    onDismissRequest:()->Unit

) {
    if(isDialogShow) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(onClick = {
                    onDismissRequest()
                })
                {
                    Text(
                        text = "Close",
                        style = TextStyle(
                            color = MChild,
                            fontFamily = Fonts.Montserrat,
                            fontSize = 14.sp
                        )
                    )
                }
            },
            title = {
                Text(text = isDialogTitle)
            },
            text = {
                Text(text = isDialogMessage)
            }
        )
    }
}
