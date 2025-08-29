package com.example.githubusersearch.uiUser

import android.app.appsearch.SearchSpec
import android.provider.ContactsContract.Profile
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes{
    const val SEARCH = "search"
    const val PROFILE = "profile/{username}"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SEARCH) {
        composable(Routes.SEARCH){
          SearchScreen(onUserClicked = {username->
              navController.navigate("detail/$username")
          })
        }
        composable(
            route = "detail/{username}",
            arguments = listOf(navArgument("username"){type =NavType.StringType } )){backStackEntry->
                val userName = backStackEntry.arguments?.getString("username").orEmpty()
                UserDetailScreen()
            }
    }
}