package com.arsildo.template

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("RestrictedApi")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var keepSplashOnScreen = true
        val delay = 2000L
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            LaunchedEffect(currentDestination) {
                val routes =
                    navController.currentBackStack.value.mapNotNull { it.destination.route }
                        .joinToString("->")
                Log.d("BackStackLog", "BackStack: $routes")
            }
            Scaffold(
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.Center
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "inputAmount",
                        ) {
                            composable(route = "inputAmount") {
                                Column {
                                    Text(
                                        "${currentDestination?.route?.uppercase()}",
                                        style = MaterialTheme.typography.headlineMedium,
                                        textAlign = TextAlign.Center
                                    )
                                    Button(
                                        onClick = { navController.navigate(route = "selectContact") }
                                    ) {
                                        Text(text = "go select contact")
                                    }

                                }
                            }
                            composable(route = "selectContact") {
                                Column {
                                    Text(
                                        "${currentDestination?.route?.uppercase()}",
                                        style = MaterialTheme.typography.headlineMedium,
                                        textAlign = TextAlign.Center
                                    )
                                    Button(
                                        onClick = navController::navigateUp
                                    ) { Text("go back") }
                                    Button(
                                        onClick = { navController.navigate("confirm") }
                                    ) {
                                        Text("confirm")
                                    }
                                }
                            }
                            composable(route = "confirm") {
                                Column {
                                    Text(
                                        "${currentDestination?.route?.uppercase()}",
                                        style = MaterialTheme.typography.headlineMedium,
                                        textAlign = TextAlign.Center
                                    )
                                    Button(
                                        onClick = navController::navigateUp
                                    ) { Text("navigateup") }
                                    Button(
                                        onClick = navController::popBackStack
                                    ) { Text("popbackstack") }
                                    Button(
                                        onClick = {
                                            navController.popBackStack()
                                            navController.navigate("inputAmount") {
                                                launchSingleTop = true
                                            }
                                        }
                                    ) {
                                        Text("Go to inputAmount")
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
