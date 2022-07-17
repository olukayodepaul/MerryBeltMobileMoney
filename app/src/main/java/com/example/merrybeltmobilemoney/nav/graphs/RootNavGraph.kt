package com.example.merrybeltmobilemoney.nav.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merrybeltmobilemoney.nav.screens.home.HomeScreen
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel
import com.example.merrybeltmobilemoney.ui.home.HomeViewModel

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    viewModel: AuthViewModel,
    localContext: Context,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(
            navController = navController,
            viewModel = viewModel,
            localContext = localContext
        )
        composable(route = Graph.HOME) {
            HomeScreen(
                viewModel =  homeViewModel,
                localContext = localContext
            )
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val TRANSFER = "details_graph"
    const val WITHDRAWER = "withdrawer_graph"
    const val BILLPAYMENT = "billpays_graph"
    const val CREDITACC = "creditacc_graph"
}