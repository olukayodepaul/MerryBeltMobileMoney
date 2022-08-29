package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.merrybeltmobilemoney.theme.*
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState


@Composable
fun OutlinedTextFieldsNumber(
    label: String,
    value:String,
    onValueChange:(String)->Unit,
    enabled:Boolean = false
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        enabled = enabled,

        onValueChange = {accNumber->
            onValueChange(accNumber)
        },

        singleLine = true,

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
                    fontFamily = Fonts.RobotoMedium,
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
            cursorColor = GreyTransparent
        )
    )
}


@Composable
fun OutlinedTextFieldsText(
    readOnly: Boolean = false,
    label: String,
    value:String,
    onValueChange:(String)->Unit,
    enabled:Boolean = false
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        readOnly = readOnly,
        modifier = Modifier.fillMaxWidth(),
        value = value,
        enabled = enabled,
        onValueChange = {accNumber->
            onValueChange(accNumber)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),

        placeholder = {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Fonts.RobotoMedium,
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
            cursorColor = GreyTransparent
        )
    )
}



@Composable
fun OutlinedTextFieldsTextPin(
    value:String,
    onValueChange:(String)->Unit,
) {

    val bColor = Borderline
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.padding(top = 5.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
        singleLine = true,
        value = value,
        onValueChange = {pin->
            onValueChange(pin)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),

        label = {
            Text(
                text = "",
                style = TextStyle(
                    fontFamily = Fonts.Montserrat,
                    color = Blues,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        },
        visualTransformation = PasswordVisualTransformation(),
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
            cursorColor = GreyTransparent
        )
    )
}


@Composable
fun Buttons(
    uiEvent: (TransferEvent)->Unit,
    uiState: TransferState,
    label: String,
) {
    Column(
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(
            onClick = {
                uiEvent(
                    TransferEvent.OnClickContButton
                )
            },
            enabled = uiState.continueButtonEnable,
            modifier = Modifier
                .fillMaxWidth(),
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

}



@Composable
fun BankList(
    uiState: TransferState,
    uiEvent: (TransferEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.specimen, //Menu Active Text
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Banks",
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
            expanded = uiState.expanded,
            onDismissRequest = {
                uiEvent(
                    TransferEvent.OnExpanded(
                        expanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.listOfBanks.forEach { specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        TransferEvent.OnExpanded(
                            expanded = false
                        )
                    )

                    uiEvent(
                        TransferEvent.OnSpecimenText(
                            specimen = specimen.name
                        )
                    )

                    uiEvent(
                        TransferEvent.OnSelectedBankLogo(
                            bankLogo = specimen.url!!
                        )
                    )

                    uiEvent(
                        TransferEvent.OnSetBankCode(
                            setBankCode = specimen.code
                        )
                    )

                    uiEvent(
                        TransferEvent.OnSetBankName(
                            setBankName = specimen.name
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
                            text = specimen.name,
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
                            TransferEvent.OnExpanded(
                                expanded = !uiState.expanded
                            )
                        )
                    }
                )
        )
    }
}