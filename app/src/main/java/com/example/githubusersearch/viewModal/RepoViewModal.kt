package com.example.githubusersearch.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearch.data.remote.GitHubApi
import com.example.githubusersearch.data.remote.Repo
import com.example.githubusersearch.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoViewModal @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _repos= MutableStateFlow<List<Repo>>(emptyList())
    val repos : StateFlow<List<Repo>> = _repos

    private val _isLoading = MutableStateFlow(false)
    val isLoading:StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadUserRepos(
        userName: String,
        page: Int = 1,
        perPage: Int = 30,
        sort: String = "updated"
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val repos = repository.getUserRepos(userName, page, perPage, sort)
                _repos.value = repos
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}