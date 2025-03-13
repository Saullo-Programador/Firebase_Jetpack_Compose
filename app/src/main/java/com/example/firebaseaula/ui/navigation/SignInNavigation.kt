package com.example.firebaseaula.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.firebaseaula.ui.screens.SignInScreen
import com.example.firebaseaula.ui.viewModel.SignInViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.signInScreen(

    onNavigateToSignUp: () -> Unit
) {
    composable(AppGraph.auth.SIGN_IN) { // Usando AppGraph para definir a rota corretamente
        val viewModel: SignInViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()


        SignInScreen(
            uiState = uiState,
            onSignInClick = {
                scope.launch {
                    viewModel.signIn()
                }
            },
            onSignUpClick = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToSignIn(navOptions: NavOptions? = null) {
    navigate(AppGraph.auth.SIGN_IN, navOptions)
}
