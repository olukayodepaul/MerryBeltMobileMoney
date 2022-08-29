package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_ui

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
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataState
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnState

@Composable
fun ProductLis(
    uiState: PhcnState,
    uiEvent: (PhcnEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.productSelected.uppercase(), //Menu Active Text
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Product",
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
            expanded = uiState.productExpanded,
            onDismissRequest = {
                uiEvent(
                    PhcnEvent.OnProductExpanded(
                        productExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.productList.forEach { specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        PhcnEvent.OnProductExpanded(
                            productExpanded = false
                        )
                    )

                    uiEvent(
                        PhcnEvent.OnProductSelected(
                            productSelected = specimen.description!!
                        )
                    )

                    uiEvent(
                        PhcnEvent.OnProductImage(
                            productImage = specimen.imageUrl!!
                        )
                    )

                    uiEvent(
                        PhcnEvent.OnProductCategory(
                            productCategory = specimen.category!!
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
                            text = specimen.description!!.uppercase(),
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
                            PhcnEvent.OnProductExpanded(
                                productExpanded = !uiState.productExpanded
                            )
                        )
                    }
                )
        )
    }
}



@Composable
fun phcnInput(
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
fun MeterType(
    uiState: PhcnState,
    uiEvent: (PhcnEvent) -> Unit,
) {
    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.meterTypeSelected, //Menu Active Text
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Meter Type",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = Fonts.RobotoBold,
                        fontWeight = FontWeight.W600
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
            expanded = uiState.meterTypeExpanded,
            onDismissRequest = {
                uiEvent(
                    PhcnEvent.OnMeterTypeExpanded(
                        meterTypeExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.meterType.forEach { content ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        PhcnEvent.OnMeterTypeExpanded(
                            meterTypeExpanded = false
                        )
                    )

                    uiEvent(
                        PhcnEvent.OnMeterTypeSelected(
                            meterTypeSelected = content.type
                        )
                    )

                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Text(
                            text = content.type,
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
                            PhcnEvent.OnMeterTypeExpanded(
                                meterTypeExpanded = !uiState.meterTypeExpanded
                            )
                        )
                    }
                )
        )
    }
}


@Composable
fun phcnSubmitButton(
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