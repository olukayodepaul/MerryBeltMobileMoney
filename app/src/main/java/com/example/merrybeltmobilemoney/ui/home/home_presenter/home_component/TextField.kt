package com.example.merrybeltmobilemoney.ui.home.home_presenter.home_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Blues
import com.example.merrybeltmobilemoney.theme.Borderline
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.GreyTransparent


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



        placeholder = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),

        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Fonts.Montserrat,
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
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),

        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Fonts.Montserrat,
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