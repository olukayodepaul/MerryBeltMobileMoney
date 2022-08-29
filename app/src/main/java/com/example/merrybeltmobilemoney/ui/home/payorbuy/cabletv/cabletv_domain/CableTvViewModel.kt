package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_domain

import androidx.lifecycle.ViewModel
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class CableTvViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {

    var uiState = MutableStateFlow(CableTvState())

    fun cableTvEventHandler(cabletvEvent: CableTvEvent) {
        when (cabletvEvent) {

        }
    }

}