package com.example.merrybeltmobilemoney.nav.graphs

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.merrybeltmobilemoney.nav.screens.ScreenContent
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component.LoginScreenTheme


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    viewModel: AuthViewModel,
    localContext: Context
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreenTheme(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                viewModel = viewModel,
            )
        }
//        composable(route = AuthScreen.SignUp.route) {
//            ScreenContent(name = AuthScreen.SignUp.route) {}
//        }
//        composable(route = AuthScreen.Forgot.route) {
//            ScreenContent(name = AuthScreen.Forgot.route) {}
//        }

//Navigation////
    // onSignUpClick = {
//////                    navController.navigate(AuthScreen.SignUp.route)
//////                },
//////                onForgotClick = {
//////                    navController.navigate(AuthScreen.Forgot.route)
//////                },
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}