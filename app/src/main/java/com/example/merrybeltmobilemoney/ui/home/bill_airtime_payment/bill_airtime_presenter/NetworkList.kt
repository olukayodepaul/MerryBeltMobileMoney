package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Borderline
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.GreyTransparent
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEventState

@Composable
fun NetworkList (
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.ntSpecimen, //Menu Active Text
            onValueChange = {},
            label = {
                Text(
                    text = "",
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

            uiState.networkList.forEach {contents ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        BillAirtimeEvent.OnNtExpanded(
                            ntExpanded = false
                        )
                    )

                    uiEvent(
                        BillAirtimeEvent.OnNtSpecimen(
                            ntSpecimen = contents.category!!
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