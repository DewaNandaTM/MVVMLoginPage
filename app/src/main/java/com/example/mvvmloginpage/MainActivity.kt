package com.example.mvvmloginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmloginpage.data.local.database.AppDatabase
import com.example.mvvmloginpage.data.repository.UserRepository
import com.example.mvvmloginpage.ui.screen.LoginScreen
import com.example.mvvmloginpage.viewmodel.LoginViewModel
import com.example.mvvmloginpage.viewmodel.LoginViewModelFactory
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database =
            AppDatabase.getDatabase(this)
        val repository =
            UserRepository(
                database.userDao()
            )
        val factory =
            LoginViewModelFactory(repository)
        setContent {
            val viewModel: LoginViewModel =
                viewModel(factory = factory)
            androidx.compose.runtime.LaunchedEffect(Unit) {
                viewModel.insertDummyUser()
            }
            LoginScreen(viewModel)
        }
    }
}