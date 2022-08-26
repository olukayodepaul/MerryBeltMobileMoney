package com.example.merrybeltmobilemoney.ui.home.payorpurchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MaterialBg
import com.example.merrybeltmobilemoney.theme.changeBgColor
import com.example.merrybeltmobilemoney.theme.getNameInitialsBg


@Composable
fun EachNavigations(
    label: String,
    link:()->Unit
) {

    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(160.dp).width(160.dp)
            .padding(4.dp)
            .clickable {
                link()
            }
    ) {
        Column(
            modifier = Modifier
                .background(changeBgColor(5))
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
        ) {

            Image(
                painterResource(id = getNameInitialsBg(1)),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                contentDescription = "Logo",
            )

            Text(
                text = label,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = Fonts.RobotoBold,
                    color = MaterialBg
                )
            )

        }
    }
}