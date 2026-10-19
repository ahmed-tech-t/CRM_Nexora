package com.example.crm_nexora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.crm_nexora.ui.navigation.AppNavGraph
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CRM_NexoraTheme {
                AppNavGraph()
            }
        }
    }
}
