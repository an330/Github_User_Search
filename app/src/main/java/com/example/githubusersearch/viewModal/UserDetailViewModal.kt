package com.example.githubusersearch.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearch.data.remote.UserDetail
import com.example.githubusersearch.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModal@Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _user = MutableStateFlow<UserDetail?>(null)
    val user = _user.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadUser(userName:String){
        viewModelScope.launch {
            try {
                _user.value = repository.getUserDetail(userName)
            }catch (t:Throwable){
                _error.value = t.message ?: "Failed to load user"
            }
        }
    }

}