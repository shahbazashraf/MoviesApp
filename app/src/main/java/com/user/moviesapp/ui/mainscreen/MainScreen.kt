package com.user.moviesapp.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.user.data.model.MediaItem
import com.user.moviesapp.viewmodel.MediaViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MediaViewModel = hiltViewModel(),
    onItemClick: (MediaItem) -> Unit
) {
    val searchQuery = remember { mutableStateOf("") }
    val mediaResults by viewModel.mediaItems.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        SearchBox(searchQuery.value, { query ->
            searchQuery.value = query
            viewModel.searchMedia(query)
        }, onClearClick = {
            searchQuery.value = ""
        })
        Spacer(modifier = Modifier.height(16.dp))

        MediaResultsScreen(mediaResults, onItemClick)
    }
}

@Composable
fun SearchBox(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text(text = "Search...") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClearClick) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Clear Text")
                }
            }
        }
    )
}
