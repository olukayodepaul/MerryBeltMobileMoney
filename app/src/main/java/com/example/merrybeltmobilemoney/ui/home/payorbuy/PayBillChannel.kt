package com.example.merrybeltmobilemoney.ui.home.payorbuy


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White

@Composable
fun PayBillChannelHome(
    airtime:()->Unit,
    datapurchase:()->Unit,
    phcn:()->Unit,
    cabletv:()->Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Airtime, Data and Bill Payment",
                        style = TextStyle(
                            fontFamily = Fonts.Montserrat,
                            fontSize = 14.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = White)
                    }
                },
                backgroundColor = MChild,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }, content = {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    //.clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .background(White)
                //horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement  =  Arrangement.SpaceAround
                ){
                    EachNavigations(
                        label="PHCN",
                        link = {
                            phcn()
                        }
                    )

                    EachNavigations(
                        label="Cable TV",
                        link = {
                            cabletv()
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement  =  Arrangement.SpaceAround
                ){
                    EachNavigations(
                        label="Airtime",
                        link = {
                            airtime()
                        }
                    )

                    EachNavigations(
                        label="Purchase Data",
                        link = {
                            datapurchase()
                        }
                    )

                }
            }
        }
    )
}