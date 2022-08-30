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


@Composable
fun dataProductLis(
    uiState: DataState,
    uiEvent: (DataEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.dataProductSelected.uppercase(), //Menu Active Text
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
            expanded = uiState.dataProductExpanded,
            onDismissRequest = {
                uiEvent(
                    DataEvent.OnDataProductExpanded(
                        dataProductExpanded = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.dataList.forEachIndexed { index, specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        DataEvent.OnDataProductExpanded(
                            dataProductExpanded = false
                        )
                    )

                    uiEvent(
                        DataEvent.OnDataProductSelected(
                            dataProductSelected = specimen.category!!,
                            dataProductImage = specimen.imageUrl!!,
                            dataProductCategory = specimen.category,
                            dataIndex = index
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
                            DataEvent.OnDataProductExpanded(
                                dataProductExpanded = !uiState.dataProductExpanded
                            )
                        )
                    }
                )
        )
    }
}


@Composable
fun dataPlanList(
    uiState: DataState,
    uiEvent: (DataEvent) -> Unit,
) {

    val bColor = Borderline

    Box {
        OutlinedTextField(
            value = uiState.dataProductSelectedPlan.uppercase(), //Menu Active Text
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
            expanded = uiState.dataProductExpandedPlan,
            onDismissRequest = {
                uiEvent(
                    DataEvent.OnDataProductExpandedPlan(
                        dataProductExpandedPlan = false
                    )
                )
            },
            Modifier.fillMaxWidth()
        ) {

            uiState.dataProductPlan.forEachIndexed { index, specimen ->
                DropdownMenuItem(onClick = {

                    uiEvent(
                        DataEvent.OnDataProductExpandedPlan(
                            dataProductExpandedPlan = false
                        )
                    )

                    uiEvent(
                        DataEvent.OnDataProductSelectedPlan(
                            dataProductSelectedPlan = "${specimen.packages} - ${specimen.duration} - ₦${specimen.price}",
                            dataProductPlanId = specimen.id!!,
                            dataProductPlanPrice = specimen.price!!
                        )
                    )

                }) {
                    Row(
                        Modifier
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "${specimen.packages} - ${specimen.duration} - ₦${specimen.price}",
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
                            DataEvent.OnDataProductExpandedPlan(
                                dataProductExpandedPlan = !uiState.dataProductExpandedPlan
                            )
                        )
                    }
                )
        )
    }
}



@Composable
fun dataInput(
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
fun dataSubmitButton(
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



