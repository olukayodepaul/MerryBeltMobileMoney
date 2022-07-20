package com.example.merrybeltmobilemoney.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.merrybeltmobilemoney.nav.graphs.RootNavigationGraph
import com.example.merrybeltmobilemoney.theme.MerryBeltMobileMoneyTheme
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel
import com.example.merrybeltmobilemoney.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val  viewModel: AuthViewModel = hiltViewModel()
            val localContext: Context = LocalContext.current


            MaterialTheme {
                MerryBeltMobileMoneyTheme {
                    RootNavigationGraph(
                        navController = rememberNavController(),
                        viewModel = viewModel,
                        localContext = localContext
                    )
                }
            }
        }
    }
}
