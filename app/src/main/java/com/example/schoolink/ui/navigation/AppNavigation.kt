package com.example.schoolink.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolink.ui.authentication.CreateAccountScreen
import com.example.schoolink.ui.authentication.LoginScreen
import com.example.schoolink.ui.onboarding.OnboardingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable(
            "onboarding",
            enterTransition = { EnterTransition.None },
            popEnterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            OnboardingScreen(
                onNavigationToLogin = {
                    navController.navigateSingleTopTo("login")
                },
                onNavigationToCreateAccount = {
                    navController.navigateSingleTopTo("createAccount")
                }
            )
        }

        composable(
            route = "login",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            }
        ) {
            LoginScreen(
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToCreateAccount = {
                    navController.navigateSingleTopTo("createAccount")
                }
            )
        }

        composable(
            route = "createAccount",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            }
        ) {
            CreateAccountScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavController.navigateSingleTopTo(route: String) {
    if (this.currentBackStackEntry?.destination?.route != route) {
        this.navigate(route) {
            launchSingleTop = true
        }
    }
}
