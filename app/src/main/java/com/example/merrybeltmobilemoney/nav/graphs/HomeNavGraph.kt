package com.example.merrybeltmobilemoney.nav.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.merrybeltmobilemoney.nav.BottomBarScreen
import com.example.merrybeltmobilemoney.ui.home.creditacc.creditacc_ui.CreditAccChannel
import com.example.merrybeltmobilemoney.ui.home.dashboard.dashboard_ui.HomeScreen
import com.example.merrybeltmobilemoney.ui.home.dashboard.dashboard_ui.ProfileScreen
import com.example.merrybeltmobilemoney.ui.home.dashboard.dashboard_ui.SettingScreen
import com.example.merrybeltmobilemoney.ui.home.payorbuy.PayBillChannelHome
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_ui.AirtimeComponentActivity
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_ui.DataPurchaseComponentActivity
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_ui.CableTvComponentActivity
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_ui.PhcnComponentActivity
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_ui.transferComposableActivity
import com.example.merrybeltmobilemoney.ui.home.withdraw.withdraw_ui.withrawChannel


@Composable
fun HomeNavGraph(
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
            localContext = localContext,
        )
    }
}

fun NavGraphBuilder.detailsNavGraph(
    localContext: Context,
    navController: NavHostController
) {
    navigation(
        route = Graph.TRANSFER,
        startDestination = DetailsScreen.Transfers.route
    ) {

        composable(route = DetailsScreen.Transfers.route) {
            transferComposableActivity()
        }

        composable(route = DetailsScreen.AccCredit.route) {
            CreditAccChannel()
        }

        composable(route = DetailsScreen.PayBill.route) {
            PayBillChannelHome(
                airtime = {
                 navController.navigate(DetailsScreen.Airtime.route)
                },
                datapurchase = {
                    navController.navigate(DetailsScreen.Data.route)
                },
                phcn = {
                    navController.navigate(DetailsScreen.Phcn.route)
                },
                cabletv = {
                    navController.navigate(DetailsScreen.Cabletv.route)
                }
            )
        }

        composable(route = DetailsScreen.Withdraw.route) {
            withrawChannel()
        }

        composable(route = DetailsScreen.Airtime.route) {
            AirtimeComponentActivity()
        }

        composable(route = DetailsScreen.Data.route) {
            DataPurchaseComponentActivity()
        }

        composable(route = DetailsScreen.Phcn.route) {
            PhcnComponentActivity()
        }

        composable(route = DetailsScreen.Cabletv.route) {
            CableTvComponentActivity()
        }

    }
}

sealed class DetailsScreen(val route: String) {
    object Transfers : DetailsScreen(route = "TRANSFER")
    object Withdraw : DetailsScreen(route = "WITHDRAW")
    object AccCredit : DetailsScreen(route = "ACCCREDIT")
    object PayBill : DetailsScreen(route = "PAYBILL")
    object Airtime : DetailsScreen(route = "AIRTIME")
    object Data : DetailsScreen(route = "DATA")
    object Phcn: DetailsScreen(route = "PHCN")
    object Cabletv: DetailsScreen(route = "CABLETV")
}
