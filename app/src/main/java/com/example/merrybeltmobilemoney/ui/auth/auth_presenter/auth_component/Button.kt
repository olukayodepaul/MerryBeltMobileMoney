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
import com.example.merrybeltmobilemoney.theme.Fonts
import com.example.merrybeltmobilemoney.theme.MChild
import com.example.merrybeltmobilemoney.theme.White
import com.example.merrybeltmobilemoney.ui.auth.auth_data.AuthState
import com.example.merrybeltmobilemoney.ui.auth.auth_presenter.AuthViewModel
import com.example.merrybeltmobilemoney.util.EncryptionUtil


@Composable
fun AuthenticationButtons(
    title:String = "",
    viewModel: AuthViewModel,
    uiState: AuthState,
) {
    Button(
        onClick = {
            viewModel.authApiRequest(uiState.username, EncryptionUtil().passwordM5D(uiState.password))
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

