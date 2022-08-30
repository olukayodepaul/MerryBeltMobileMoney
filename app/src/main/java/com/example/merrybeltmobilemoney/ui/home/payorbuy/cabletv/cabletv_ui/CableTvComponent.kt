package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_ui


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
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvState


@Composable
fun CableTvProductLis(
    uiState: CableTvState,
    uiEvent: (CableTvEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.cableTvProductSelected.uppercase(), //Menu Active Text
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
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
            expanded = uiState.cableTvProductExpanded,
            onDismissRequest = {
                uiEvent(
                    CableTvEvent.OnCableTvProductExpanded(
                        cableTvProductExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.cableTvList.forEachIndexed { index, specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        CableTvEvent.OnCableTvProductExpanded(
                            cableTvProductExpanded = false
                        )
                    )

                    uiEvent(
                        CableTvEvent.OnCableTvProductSelected(
                            cableTvProductSelected = specimen.category!!,
                            cableTvIndex = index,
                            cableTvProductCategory = specimen.category!!,
                            cableTvProductImage = specimen.imageUrl!!
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
                            text = specimen.category!!.uppercase(),
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
                            CableTvEvent.OnCableTvProductExpanded(
                                cableTvProductExpanded = !uiState.cableTvProductExpanded
                            )
                        )
                    }
                )
        )
    }
}


@Composable
fun CableTvProductLisPlan(
    uiState: CableTvState,
    uiEvent: (CableTvEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.cableTvProductSelectedPlan.uppercase(), //Menu Active Text
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
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
            expanded = uiState.cableTvProductExpandedPlan,
            onDismissRequest = {
                uiEvent(
                    CableTvEvent.OnCableTvProductExpandedPlan(
                        cableTvProductExpandedPlan = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.cableTvProductPlan.forEachIndexed { index, specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        CableTvEvent.OnCableTvProductExpandedPlan(
                            cableTvProductExpandedPlan = false
                        )
                    )

                    uiEvent(
                        CableTvEvent.OnCableTvProductSelectedPlan(
                            cableTvProductSelectedPlan = "${specimen.packages!!.uppercase()}  ${specimen.price!!.uppercase()}",
                            cableTvProductPlanId = specimen.id!!,
                            cableTvProductPlanPrice = specimen.price!!
                        )
                    )


                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "${specimen.packages!!.uppercase()} ${specimen.price!!.uppercase()}",
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
                            CableTvEvent.OnCableTvProductExpandedPlan(
                                cableTvProductExpandedPlan = !uiState.cableTvProductExpandedPlan
                            )
                        )
                    }
                )
        )
    }
}



@Composable
fun cableTvInput(
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
fun spanceWidget(
    label: String
){
    Text(
        text = label,
        Modifier.padding(bottom = 4.dp, top = 2.dp),
        style = TextStyle(
            fontFamily = Fonts.RobotoNormal,
            fontSize = 15.sp,
            color = Monsoon
        )
    )
}


@Composable
fun cableTvSubmitButton(
    //submit: () -> Unit,
    label: String,
) {
    Button(
        onClick = {
           // submit()
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
