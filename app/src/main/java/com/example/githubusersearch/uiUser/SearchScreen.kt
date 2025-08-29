package com.example.githubusersearch.uiUser

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SearchScreen(
    onUserClicked:(String)->Unit,
    viewModel: ViewModel= hiltViewModel()
) {
      val query by viewModel.query
}