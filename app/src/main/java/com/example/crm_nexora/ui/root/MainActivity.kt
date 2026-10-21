package com.example.crm_nexora.ui.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.crm_nexora.ui.components.chrome.LanguageWrapper
import com.example.crm_nexora.ui.navigation.AppNavGraph
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val rootViewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CRM_NexoraTheme {
                    RootScreen(viewModel = rootViewModel)
            }
        }
    }
}
