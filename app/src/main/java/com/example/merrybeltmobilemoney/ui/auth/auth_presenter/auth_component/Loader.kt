package com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component


import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.merrybeltmobilemoney.theme.MChild


@Composable
fun CircularPropagations(
    status: Boolean
){
    if(status){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                color = MChild
            )
        }
    }
}