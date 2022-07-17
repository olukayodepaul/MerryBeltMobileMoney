package com.example.merrybeltmobilemoney.ui.home.home



import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.merrybeltmobilemoney.nav.graphs.DetailsScreen
import com.example.merrybeltmobilemoney.nav.graphs.Graph
import com.example.merrybeltmobilemoney.theme.Fonts.Montserrat
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoBold
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.home.HomeViewModel
import com.example.merrybeltmobilemoney.ui.home.home_presenter.home_component.appIntents


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    localContext: Context,
    navController: NavHostController
) {

    val uiSate = viewModel.uiState.collectAsState().value
    val uiEvent = viewModel::homeEventHandler

    Column(
        modifier = Modifier
            .background(color = MChild)
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {

        Column(modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .background(color = MChild)
            .padding(20.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = "Available Balance",
                modifier = Modifier
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = RobotoBold,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 5.dp))

            Text(text = uiSate.customerId,
                modifier = Modifier
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = Montserrat,
                    color = White,
                    fontWeight = FontWeight.Bold,
                )
            )

            Text(
                text = uiSate.balance,
                modifier = Modifier
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = RobotoBold,
                    color = White
                )
            )
        }

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .background(White)
                    .padding(start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                Spacer(modifier = Modifier.padding(bottom = 45.dp))
                appIntents(
                    imageSwitchers = 2,
                    routerLinkName = "Transfer",
                    click = {
                        navController.navigate(Graph.TRANSFER)
                    },
                )

                Spacer(modifier = Modifier.padding(bottom = 15.dp))

                appIntents(
                    imageSwitchers = 1,
                    routerLinkName = "Bill Payment",
                     click = {
                        navController.navigate(DetailsScreen.PayBill.route)
                    },
                )

                Spacer(modifier = Modifier.padding(bottom = 15.dp))

                appIntents(
                    imageSwitchers = 3,
                    routerLinkName = "Withdraw",
                    click = {
                        navController.navigate(DetailsScreen.Withdraw.route)
                    },
                )

                Spacer(modifier = Modifier.padding(bottom = 15.dp))

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
