package com.example.merrybeltmobilemoney.ui.home.home_presenter.home_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White

@Composable
fun Buttons(
    label: String,
    enabled: Boolean = false,

) {
    Button(
        onClick = {

        },
        enabled = enabled,
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