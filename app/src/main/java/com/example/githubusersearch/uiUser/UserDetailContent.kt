package com.example.githubusersearch.uiUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubusersearch.data.UserDetail
import com.example.githubusersearch.data.repository.UserRepository

@Composable
fun UserDetailContent(user:UserDetail, onOpenRepo:(String) ->Unit) {

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {  }
      Row {
          AsyncImage(
              model = user.avatarUrl,
              contentDescription = null,
              modifier = Modifier.size(96.dp).clip(CircleShape)
          )
          Spacer(Modifier.width(16.dp))
          Column {
              Text(text = user.name ?: user.login, style = MaterialTheme.typography.headlineSmall)
              Spacer(Modifier.height(8.dp))
              Text(text = user.bio ?: "No bio", style = MaterialTheme.typography.bodyMedium)
              Spacer(Modifier.height(8.dp))
              Text(text = "Followers: ${user.followers}  •  Repos: ${user.publicRepo}", style = MaterialTheme.typography.bodySmall)
          }
          Spacer(Modifier.height(16.dp))


          Button(onClick = { onOpenRepo("https://github.com/${user.login}") }) {
              Text("Open on GitHub")
          }
      }
}