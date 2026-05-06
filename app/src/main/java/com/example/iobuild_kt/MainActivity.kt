package com.example.iobuild_kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.iobuild_kt.core.i18n.LanguageManager
import com.example.iobuild_kt.core.i18n.LocalLanguage
import com.example.iobuild_kt.core.ui.navigation.NavGraph
import com.example.iobuild_kt.ui.theme.IoBuildktTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val languageManager: LanguageManager = koinInject()
            val currentLang by languageManager.currentLanguage.collectAsState(initial = "es")
            val scope = rememberCoroutineScope()

            IoBuildktTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                ) {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        currentLang = currentLang,
                        onLanguageChange = { lang ->
                            scope.launch { languageManager.setLanguage(lang) }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    IoBuildktTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            val navController = rememberNavController()
            NavGraph(
                navController = navController,
                currentLang = "es",
                onLanguageChange = {}
            )
        }
    }
}
