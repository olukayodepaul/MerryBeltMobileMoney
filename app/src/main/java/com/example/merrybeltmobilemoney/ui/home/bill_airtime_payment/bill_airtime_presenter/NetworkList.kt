package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_presenter

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEventState
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState

@Composable
fun NetworkList (
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit,
    label: String
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.ntSpecimen, //Menu Active Text
            onValueChange = {},
//            label = {
//                Text(
//                    text = "",
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontFamily = Fonts.RobotoBold,
//                        fontWeight = FontWeight.W600
//                    )
//                )
//            },

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
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
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
            expanded = uiState.ntExpanded,
            onDismissRequest = {
                uiEvent(
                    BillAirtimeEvent.OnNtExpanded(
                        ntExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.networkList.forEachIndexed { index, contents ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        BillAirtimeEvent.OnNtExpanded(
                            ntExpanded = false
                        )
                    )

                    uiEvent(
                        BillAirtimeEvent.OnNtSpecimen(
                            ntSpecimen = contents.category!!,
                            index = index
                        )
                    )

                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Text(
                            text = contents.category!!,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = Fonts.RobotoBold,
                                fontWeight = FontWeight.W600
                            )
                        )
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
                            BillAirtimeEvent.OnNtExpanded(
                                ntExpanded = !uiState.ntExpanded
                            )
                        )
                    }
                )
        )
    }
}


@Composable
fun DataPlanSpinner (
    label: String,
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.dtSetText, //Menu Active Text
            onValueChange = {},
//            label = {
//                Text(
//                    text = "",
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontFamily = Fonts.RobotoBold,
//                        fontWeight = FontWeight.W600
//                    )
//                )
//            },
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
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
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
            expanded = uiState.dtExpanded,
            onDismissRequest = {
                uiEvent(
                    BillAirtimeEvent.OnDtExpanded(
                        dtExpanded  = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.dataPlanList.forEach {contents ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        BillAirtimeEvent.OnDtExpanded(
                            dtExpanded  = false
                        )
                    )

                    uiEvent(
                        BillAirtimeEvent.OnDtSetText(
                            dtSetText = "${contents.packages} - ${contents.duration} - ₦${contents.price}"
                        )
                    )

                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "${contents.packages} - ${contents.duration} - ₦${contents.price}",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = Fonts.RobotoBold,
                                fontWeight = FontWeight.W600
                            )
                        )
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
                            BillAirtimeEvent.OnDtExpanded(
                                dtExpanded = !uiState.dtExpanded
                            )
                        )
                    }
                )
        )
    }
}


@Composable
fun BillPaymentOnlineText(
    label: String,
    value:String,
    onValueChange:(String)->Unit,
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp).background(MaterialBg).focusable(enabled = true),
        value = value,
        onValueChange = {onValuesChange->
            onValueChange(onValuesChange)
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
fun DataButtons(
    //uiEvent: (TransferEvent)->Unit,
    label: String,
) {
    Button(
        onClick = {
//            uiEvent(
//                TransferEvent.OnClickContButton
//            )
        },
        modifier = Modifier
            .fillMaxWidth().padding(top = 20.dp),
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

