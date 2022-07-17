package com.example.merrybeltmobilemoney.ui.home.home

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ProfileScreen(
    name: String,
    onClick: () -> Unit
) {
    Text(text = "ProfileScreen",
        modifier = Modifier.clickable { onClick() },
    )
}