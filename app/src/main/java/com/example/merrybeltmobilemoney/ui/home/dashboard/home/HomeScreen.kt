package com.example.merrybeltmobilemoney.ui.home.dashboard.home



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merrybeltmobilemoney.nav.graphs.DetailsScreen
import com.example.merrybeltmobilemoney.nav.graphs.Graph
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.home.presenters.HomeViewModel
import com.example.merrybeltmobilemoney.ui.home.presenters.home_component.appIntents


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiSate = homeViewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .background(color = MChild)
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {

        Column(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(color = MChild)
            .padding(20.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = "Available Balance",
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = White,
                    fontFamily = Fonts.RobotoNormal,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.padding(bottom = 5.dp))

            Text(text = uiSate.accountNumber,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Fonts.RobotoNormal,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )

            Text(
                text = "₦${uiSate.balances}",
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = Fonts.RobotoMedium,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )
        }
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
                    horizontalArrangement  =  Arrangement.SpaceBetween
                ){

                    appIntents(
                        imageSwitchers = 2,
                        routerLinkName = "Transfer",
                        click = {
                            navController.navigate(Graph.TRANSFER)
                        },
                    )

                    appIntents(
                        imageSwitchers = 1,
                        routerLinkName = "Bill DAta AND Airtime",
                        click = {
                            navController.navigate(DetailsScreen.PayBill.route)
                        },
                    )
                }

                Row(  modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement  =  Arrangement.SpaceBetween){
                    appIntents(
                        imageSwitchers = 3,
                        routerLinkName = "Withdraw",
                        click = {
                            navController.navigate(DetailsScreen.Withdraw.route)
                        },
                    )


                    appIntents(
                        imageSwitchers = 0,
                        routerLinkName = "Credit Account",
                        click = {
                            navController.navigate(DetailsScreen.AccCredit.route)
                        }
                    )
                }
            }

    }
}
