package com.example.merrybeltmobilemoney.ui.home.home

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SettingScreen(
    name: String,
    onClick: () -> Unit
) {
    Text(text = "SettingScreen",
        modifier = Modifier.clickable { onClick() },
    )
}