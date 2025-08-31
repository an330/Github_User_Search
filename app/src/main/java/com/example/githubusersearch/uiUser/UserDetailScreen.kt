package com.example.githubusersearch.uiUser

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.githubusersearch.viewModal.UserDetailViewModal
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    navController: NavController,
    userName:String,
    onBack:() ->Unit ={},
    viewModal: UserDetailViewModal= hiltViewModel()
) {
    val user by viewModal.user.collectAsState()
    val error by viewModal.error.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(userName) {
        viewModal.loadUser(userName)
    }
    Scaffold(topBar = { TopAppBar(
        title = { Text(userName) }, navigationIcon = { IconButton(onClick = onBack){ Icon(Icons.Default.ArrowBack
        , contentDescription = null) } }
    ) } ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)){
            when{
                error != null -> {
                    Text(text =error?: "Error", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
                }
                user == null -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                else ->{
                    user?.let { user ->
                        UserDetailContent(
                            user = user,
                            onOpenRepo = { login ->
                                navController.navigate("repos/$login")   // ✅ navigate properly
                            }
                        )
                    }
                }
            }
        }

    }
}

