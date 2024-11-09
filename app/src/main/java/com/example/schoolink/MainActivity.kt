package com.example.schoolink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.schoolink.ui.navigation.AppNavigation
import com.example.schoolink.ui.theme.SchoolinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolinkTheme {
                AppNavigation()
            }
        }
    }
}
