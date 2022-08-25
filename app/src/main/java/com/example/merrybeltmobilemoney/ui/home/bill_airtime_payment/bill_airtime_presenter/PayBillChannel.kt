package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.merrybeltmobilemoney.theme.DialogBoxLoading
import com.example.merrybeltmobilemoney.theme.Fonts.Montserrat
import com.example.merrybeltmobilemoney.theme.Fonts.MontserratBold
import com.example.merrybeltmobilemoney.theme.Fonts.RobotoBold
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.MaterialBg
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEventState
import com.example.merrybeltmobilemoney.ui.home.presenters.BillAndAirtimeViewModel

@Composable
fun PayBillChannel(
    bill: BillAndAirtimeViewModel = hiltViewModel()
){

    val uiState = bill.uiState.collectAsState().value
    val uiEvent = bill::billAirtimeEventHandler

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Data, Airtime and Bill Payment",
                            style = TextStyle(
                                fontFamily = MontserratBold,
                                fontSize = 15.sp,

                                )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ArrowBack,"backIcon", tint = White)
                        }
                    },
                    backgroundColor = MChild,
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            }, content = {
                Column(
                    Modifier
                    .fillMaxSize()
                ) {

                    Column(
                        Modifier
                            .background(MaterialBg)
                            .padding(20.dp)
                    ) {

                        Text(
                            text = "Select a category",
                            style = TextStyle(
                                fontFamily = RobotoBold,
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = "Your airtime and data top up, cable tv and internet payment",
                            style = TextStyle (
                                fontFamily = Montserrat,
                                fontSize = 13.sp
                            )
                        )

                        PaymentCategory(uiState, uiEvent)

                        when(uiState.categoryId) {
                            1->{
                                loadAirtime(uiState, uiEvent)
                            }
                            2->{
                                loadData(uiState, uiEvent)
                            }
                            3->{
                                cableTv(uiState, uiEvent)
                            }
                            4->{
                                getPhcn(uiState, uiEvent)
                            }
                        }
                    }
                }
            }
        )
    }
}


@Composable
fun loadData(
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit
){



    Column(

        Modifier
            .fillMaxSize()
           // .background(White)
           // .padding(10.dp)
    ) {
//        Text(
//            text = "Load Internet Data",
//            style = TextStyle(
//                fontFamily = Montserrat,
//                fontSize = 18.sp,
//                textDecoration = TextDecoration.Underline,
//                color = MChild
//            )
//        )

        uiEvent(
            BillAirtimeEvent.OnChangeCategory(
                onChangeCategory = uiState.categoryId
            )
        )

        NetworkList(
            uiState, uiEvent, "Select Network"
        )

        DataPlanSpinner(
            label = "Available Bundles",
            uiState,
            uiEvent,
        )


        BillPaymentOnlineText(
            label = "Amount",
            value = uiState.dataAmount,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataAmount(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Mobile Number",
            value = uiState.dataPhoneNumber,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataPhoneNumber(it)
                )
            }
        )

        if(!uiState.loader) {
            DialogBoxLoading()
        }

        DataButtons(label = "Next")

    }
}

@Composable
fun loadAirtime(
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit
){


    Column(

        Modifier
            .fillMaxSize()
        // .background(White)
        // .padding(10.dp)
    ) {


        uiEvent(
            BillAirtimeEvent.OnChangeCategory(
                onChangeCategory = uiState.categoryId
            )
        )

        NetworkList(
            uiState, uiEvent, "Select Network"
        )

        BillPaymentOnlineText(
            label = "Amount",
            value = uiState.dataAmount,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataAmount(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Mobile Number",
            value = uiState.dataPhoneNumber,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataPhoneNumber(it)
                )
            }
        )


        if(!uiState.loader) {
            DialogBoxLoading()
        }

        DataButtons(label = "Next")
    }
}


@Composable
fun cableTv(
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit
){


    Column(

        Modifier
            .fillMaxSize()
        // .background(White)
        // .padding(10.dp)
    ) {


        uiEvent(
            BillAirtimeEvent.OnChangeCategory(
                onChangeCategory = uiState.categoryId
            )
        )

        NetworkList(
            uiState, uiEvent, "Select Bouquet"
        )

        DataPlanSpinner(
            label = "Product",
            uiState,
            uiEvent
        )

        BillPaymentOnlineText(
            label = "Smart Card Number",
            value = uiState.dataPhoneNumber,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataPhoneNumber(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Amount",
            value = uiState.dataAmount,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataAmount(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Phone Number",
            value = uiState.dataPhoneNumber,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataPhoneNumber(it)
                )
            }
        )

        if(!uiState.loader) {
            DialogBoxLoading()
        }

        DataButtons(label = "Next")

    }
}


@Composable
fun getPhcn(
    uiState: BillAirtimeEventState,
    uiEvent: (BillAirtimeEvent)->Unit
){


    Column(

        Modifier
            .fillMaxSize()
        // .background(White)
        // .padding(10.dp)
    ) {


        uiEvent(
            BillAirtimeEvent.OnChangeCategory(
                onChangeCategory = uiState.categoryId
            )
        )

        NetworkList(
            uiState, uiEvent, "Select Category"
        )


        BillPaymentOnlineText(
            label = "Meter Number",
            value = uiState.dataPhoneNumber,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataPhoneNumber(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Amount",
            value = uiState.dataAmount,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataAmount(it)
                )
            }
        )

        BillPaymentOnlineText(
            label = "Phone Number",
            value = uiState.dataAmount,
            onValueChange = {
                uiEvent(
                    BillAirtimeEvent.OnDataAmount(it)
                )
            }
        )

        if(!uiState.loader) {
            DialogBoxLoading()
        }

        DataButtons(label = "Next")

    }
}