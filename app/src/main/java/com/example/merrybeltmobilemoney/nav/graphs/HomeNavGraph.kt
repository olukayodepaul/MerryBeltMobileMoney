package com.example.merrybeltmobilemoney.nav.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.merrybeltmobilemoney.nav.BottomBarScreen
import com.example.merrybeltmobilemoney.ui.home.HomeViewModel
import com.example.merrybeltmobilemoney.ui.home.home.HomeScreen
import com.example.merrybeltmobilemoney.ui.home.home.ProfileScreen
import com.example.merrybeltmobilemoney.ui.home.home.SettingScreen
import com.example.merrybeltmobilemoney.ui.home.home.trans_channel.CreditAccChannel
import com.example.merrybeltmobilemoney.ui.home.home.trans_channel.PayBillChannel
import com.example.merrybeltmobilemoney.ui.home.home.trans_channel.transferChannel
import com.example.merrybeltmobilemoney.ui.home.home.trans_channel.withrawChannel


@Composable
fun HomeNavGraph(
    viewModel: HomeViewModel,
    localContext: Context,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                localContext = localContext,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                name = BottomBarScreen.Profile.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingScreen(
                name = BottomBarScreen.Settings.route,
                onClick = { }
            )
        }
        detailsNavGraph(
            navController = navController,
            viewModel = viewModel,
            localContext = localContext,
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(
    viewModel: HomeViewModel,
    localContext: Context,
    navController: NavHostController
) {
    navigation(
        route = Graph.TRANSFER,
        startDestination = DetailsScreen.Transfers.route
    ) {

        composable(route = DetailsScreen.Transfers.route) {
            transferChannel(
                viewModel = viewModel,
                localContext = localContext,
                navController = navController
            )
        }

        composable(route = DetailsScreen.AccCredit.route) {
            CreditAccChannel()
        }

        composable(route = DetailsScreen.PayBill.route) {
            PayBillChannel()
        }

        composable(route = DetailsScreen.Withdraw.route) {
            withrawChannel()
        }


//        composable(route = DetailsScreen.Overview.route) {
//            ScreenContent(name = DetailsScreen.Overview.route) {
//                navController.popBackStack(
//                    route = DetailsScreen.Information.route,
//                    inclusive = false
//                )
//            }
//        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Transfers : DetailsScreen(route = "TRANSFER")
    object Withdraw : DetailsScreen(route = "WITHDRAW")
    object AccCredit : DetailsScreen(route = "ACCCREDIT")
    object PayBill : DetailsScreen(route = "PAYBILL")
   // object Overview : DetailsScreen(route = "OVERVIEW")
}
