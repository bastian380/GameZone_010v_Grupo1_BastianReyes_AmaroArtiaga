package com.gamezone.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamezone.data.AppDatabase
import com.gamezone.data.UserEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CreateUserState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isRegistrationLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val errorMessage: String? = null
)

class CreateUserViewModel : ViewModel() {
    var state by mutableStateOf(CreateUserState())
        private set

    private var database: AppDatabase? = null
    private var userDao: com.gamezone.data.UserDao? = null

    fun initializeDatabase(context: Context) {
        database = AppDatabase.getInstance(context)
        userDao = database?.userDao()
    }

    fun onNameChange(newName: String) {
        state = state.copy(name = newName, errorMessage = null)
    }

    fun onEmailChange(newEmail: String) {
        state = state.copy(email = newEmail, errorMessage = null)
    }

    fun onPasswordChange(newPassword: String) {
        state = state.copy(password = newPassword, errorMessage = null)
    }

    fun onConfirmPasswordChange(newPassword: String) {
        state = state.copy(confirmPassword = newPassword, errorMessage = null)
    }

    fun togglePasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    private suspend fun validateForm(): Boolean {
        return when {
            state.name.isBlank() -> {
                state = state.copy(errorMessage = "El nombre es obligatorio")
                false
            }
            state.email.isBlank() || !state.email.contains("@") -> {
                state = state.copy(errorMessage = "Email inválido")
                false
            }
            state.password.length < 6 -> {
                state = state.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres")
                false
            }
            state.password != state.confirmPassword -> {
                state = state.copy(errorMessage = "Las contraseñas no coinciden")
                false
            }
            isEmailRegistered(state.email) -> {
                state = state.copy(errorMessage = "Este email ya está registrado")
                false
            }
            else -> true
        }
    }

    fun registerUser(context: Context) {
        if (database == null) {
            initializeDatabase(context)
        }

        state = state.copy(isRegistrationLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                if (!validateForm()) {
                    state = state.copy(isRegistrationLoading = false)
                    return@launch
                }

                delay(1500)

                val newUser = UserEntity(
                    name = state.name.trim(),
                    email = state.email.trim(),
                    password = state.password
                )

                saveUserToDatabase(newUser)

                state = state.copy(
                    isRegistrationLoading = false,
                    registrationSuccess = true,
                    errorMessage = null
                )

                println("Usuario registrado exitosamente: ${newUser.name}")

            } catch (e: Exception) {
                state = state.copy(
                    isRegistrationLoading = false,
                    errorMessage = "Error al registrar: ${e.message}"
                )
            }
        }
    }

    private suspend fun saveUserToDatabase(user: UserEntity) {
        userDao?.insertUser(user)
    }

    private suspend fun isEmailRegistered(email: String): Boolean {
        return userDao?.getUserByEmail(email) != null
    }

    fun resetRegistrationState() {
        state = state.copy(registrationSuccess = false)
    }
}