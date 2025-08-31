package com.example.githubusersearch.uiUser

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes{
    const val SEARCH = "search"

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
                UserDetailScreen(navController,userName = userName, onBack ={ navController.popBackStack()})
            }
        composable(
            route = "repos/{username}",
            arguments = listOf(navArgument("username"){type =NavType.StringType})
        ){navBackStackEntry ->  
            val userName = navBackStackEntry.arguments?.getString("username").orEmpty()
            RepoScreen(userName)
        }
    }
}