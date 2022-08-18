package com.example.merrybeltmobilemoney.ui.home.presenters.home_component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.dashboard.home_data.HomeState
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState


@Composable
fun BankList(
    uiState: TransferState,
    uiEvent:(TransferEvent)->Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.value,
            onValueChange = { },
            label = {
                Text(
                    text = "Banks",
                    style = TextStyle(
                        fontFamily = Fonts.Montserrat,
                        color = Blues,
                        fontSize = 18.sp
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
            expanded = uiState.enable,
            onDismissRequest = {
                uiEvent(
                    TransferEvent.OnEnable(
                        enable = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {
            uiState.accountVerification.forEach { specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        TransferEvent.OnEnable(
                            enable = !uiState.enable
                        )
                    )

                    uiEvent(
                        TransferEvent.OnBankLogo(
                            bankLogo = specimen.url!!
                        )
                    )

                    uiEvent(
                        TransferEvent.OnValue(
                            value = specimen.name
                        )
                    )
                }) {
                    Row(   Modifier
                        .padding(5.dp)
                    ) {
                        Column(
                            Modifier
                            .padding(end = 10.dp)
                            .width(20.dp)
                            .height(20.dp)) {

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
                        Text(text = specimen.name)
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
                            TransferEvent.OnEnable(
                                enable = !uiState.enable
                            )
                        )
                    }
                )
        )
    }
}
