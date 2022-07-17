package com.example.merrybeltmobilemoney.ui.home.home_presenter.home_component

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
import com.example.merrybeltmobilemoney.ui.home.home_data.HomeEvent
import com.example.merrybeltmobilemoney.ui.home.home_data.HomeState


@Composable
fun BankList(
    uiStete: HomeState,
    uiEvent:(HomeEvent)->Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiStete.value,
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
            expanded = uiStete.enable,
            onDismissRequest = {
                uiEvent(
                    HomeEvent.onEnable(
                        enable = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {
            uiStete.banklist.forEach { specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        HomeEvent.onEnable(
                            enable = !uiStete.enable
                        )
                    )

                    uiEvent(
                        HomeEvent.onBankLog(
                            bankLogo = specimen.url!!
                        )
                    )

                    uiEvent(
                        HomeEvent.onValue(
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
                            HomeEvent.onEnable(
                                enable = !uiStete.enable
                            )
                        )
                    }
                )
        )
    }
}
