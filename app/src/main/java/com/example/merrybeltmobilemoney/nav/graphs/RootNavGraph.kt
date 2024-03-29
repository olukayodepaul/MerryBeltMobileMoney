package com.example.merrybeltmobilemoney.nav.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merrybeltmobilemoney.nav.screens.home.HomeScreen
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    viewModel: AuthViewModel,
    localContext: Context,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(
            navController = navController,
            viewModel = viewModel
        )
        composable(route = Graph.HOME) {
            HomeScreen(
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
}