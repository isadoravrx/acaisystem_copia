package com.example.lounge

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lounge.ui.theme.LoungeTheme
import com.example.lounge.ui.theme.screens.ChooseAProduct
import com.example.lounge.ui.theme.screens.SucoScreen
import com.example.lounge.ui.theme.screens.VitaminaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoungeTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }

}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("chooseAProduct") { ChooseAProduct(navController) }
        composable("vitaminaScreen") { VitaminaScreen(navController) }
        composable("sucoScreen") { SucoScreen(navController) }
    }
}

