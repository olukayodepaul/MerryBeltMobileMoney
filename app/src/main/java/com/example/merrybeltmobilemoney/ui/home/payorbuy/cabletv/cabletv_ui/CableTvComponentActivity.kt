package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_domain.CableTvViewModel

@Composable
fun CableTvComponentActivity(
    dataViewModel: CableTvViewModel = hiltViewModel(),
){

    val uiState = dataViewModel.uiState.collectAsState().value
    val uiEvent = dataViewModel::cableTvEventHandler



}