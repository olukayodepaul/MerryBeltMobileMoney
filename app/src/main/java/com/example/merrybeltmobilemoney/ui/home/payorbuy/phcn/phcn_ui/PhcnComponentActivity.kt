package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_domain.PhcnViewModel

@Composable
fun PhcnComponentActivity(
    dataViewModel: PhcnViewModel = hiltViewModel(),
) {

    val uiState = dataViewModel.uiState.collectAsState().value
    val uiEvent = dataViewModel::phcnEventHandler


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "PHCN",
                        style = TextStyle(
                            fontFamily = Fonts.RobotoBold,
                            fontSize = 14.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = White)
                    }
                },
                backgroundColor = MChild,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }, content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())) {
                
                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                ProductLis(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                MeterType(
                    uiState = uiState,
                    uiEvent = uiEvent
                )

                phcnInput(
                    label = "Meter Number",
                    value = uiState.meterNumber,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnMeterNumber(
                                it
                            )
                        )
                    }
                )

                phcnInput(
                    label = "Phone Number",
                    value = uiState.phoneNumber,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnPhoneNumber(
                                it
                            )
                        )
                    }
                )

                phcnInput(
                    label = "Amount",
                    value = uiState.amount,
                    onValueChange = {
                        uiEvent(
                            PhcnEvent.OnAmount(
                                it
                            )
                        )
                    }
                )

                phcnSubmitButton(
                    submit = {
                        uiEvent(
                            PhcnEvent.OnContinue,
                        )
                    },
                    label = "Continue"
                )

            }
        }
    )
}