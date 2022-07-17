package com.example.merrybeltmobilemoney.ui.home.home.trans_channel


import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.merrybeltmobilemoney.ui.home.HomeViewModel


@Composable
fun transferChannel(
    viewModel: HomeViewModel,
    localContext: Context,
    navController: NavHostController
) {
    val uiSate = viewModel.uiState.collectAsState().value
    val uiEvent = viewModel::homeEventHandler

    
}