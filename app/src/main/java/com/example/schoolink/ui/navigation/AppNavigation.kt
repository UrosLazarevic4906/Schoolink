package com.example.schoolink.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolink.data.database.AppDatabase
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.ui.screens.authentication.CreateAccountScreen
import com.example.schoolink.ui.screens.authentication.LoginScreen
import com.example.schoolink.ui.screens.authentication.ProfessorInputScreen
import com.example.schoolink.ui.screens.onboarding.OnboardingScreen
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

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
                    navController.navigateSingleTopTo("professorInput")
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

        composable(
            route = "professorInput",
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
            }
        ) {
            // Create the database and repository here
            val database = AppDatabase.getInstance(context)
            val repository = ProfessorRepository(database.professorDao())

            // Use the ViewModel factory to create an instance of the ViewModel
            val viewModelFactory = ProfessorViewModelFactory(repository)
            val viewModel: ProfessorViewModel = viewModel(factory = viewModelFactory)

            ProfessorInputScreen(viewModel = viewModel, context = context)
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
