package com.example.merrybeltmobilemoney.ui.auth.auth_presenter.auth_component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.auth.auth_data.AuthState
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel


@Composable
fun AuthenticationButtons(
    title:String = "",
    viewModel: AuthViewModel,
    uiState: AuthState,
) {
    Button(
        onClick = {
            viewModel.AuthApiRequest(uiState.username, uiState.password)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MChild
        ),
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = White,
                fontSize = 20.sp,
                fontFamily = Fonts.MontserratBold
            ),
        )
    }
}

