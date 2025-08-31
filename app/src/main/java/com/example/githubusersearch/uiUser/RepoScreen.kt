package com.example.githubusersearch.uiUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubusersearch.data.remote.Repo
import com.example.githubusersearch.viewModal.RepoViewModal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoScreen(
    login: String,
    viewModel: RepoViewModal = hiltViewModel()
) {
    val repos by viewModel.repos.collectAsState()

    LaunchedEffect(login) {
        viewModel.loadUserRepos(login)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("$login’s Repositories") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            items(repos.count()) { index ->
                val repo = repos[index]
                repo?.let { RepoRow(repo = repo) }

            }
        }
    }
}

@Composable
fun RepoRow(repo: Repo) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = repo.name, style = MaterialTheme.typography.titleMedium)
        repo.description?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Row(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("⭐ ${repo.stars}")
            Text("🍴 ${repo.forks}")
        }
    }
    Divider()
}