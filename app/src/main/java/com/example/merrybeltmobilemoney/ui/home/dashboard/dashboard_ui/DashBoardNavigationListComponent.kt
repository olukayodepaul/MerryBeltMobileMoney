package com.example.merrybeltmobilemoney.ui.home.dashboard.dashboard_ui


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
import com.example.merrybeltmobilemoney.theme.*


@Composable
fun dashBoardNavigationListComponent(
    imageSwitchers: Int = 0,
    routerLinkName: String = "",
   click:()->Unit
){
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(180.dp).width(180.dp)
            .padding(4.dp)
            .clickable {
                click()
            }
    ) {
        Column(
            modifier = Modifier
                .background(changeBgColor(switchColor = 4))
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            ) {

            Image(
                painterResource(id = getNameInitialsBg(imageSwitchers)),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                contentDescription = "Logo",
            )

            Text(
                text = routerLinkName,
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






