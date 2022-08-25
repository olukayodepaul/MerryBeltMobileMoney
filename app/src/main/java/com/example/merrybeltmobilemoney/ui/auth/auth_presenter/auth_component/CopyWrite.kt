package com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Fonts.MontserratBold
import com.example.merrybeltmobilemoney.theme.Menus


@Composable
fun copyWrite(
    copyWriteYear:String
){
    Box(modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = AnnotatedString("© $copyWriteYear Merry Belt. All Rights Reserved."),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = MontserratBold,
                color = Menus,
                textAlign = TextAlign.Center
            ),
        )
    }
}
