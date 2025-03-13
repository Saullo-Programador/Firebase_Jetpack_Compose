package com.example.firebaseaula.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebaseaula.authentication.FirebaseAuthRepository
import com.example.firebaseaula.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class AppState(
    val user: User? = null,
    val isInitLoading: Boolean = true
)

@HiltViewModel
class AppViewModel @Inject constructor(
    firebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state
        .combine(firebaseAuthRepository.currentUser){ appState, authResult ->
            val user = authResult.currentUser?.email?.let{User(it)}
            appState.copy(user = user, isInitLoading = authResult.isInitLoading)
        }

}