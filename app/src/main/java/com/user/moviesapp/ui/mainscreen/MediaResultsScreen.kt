package com.user.moviesapp.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.user.data.constant.Constants.IMAGE_BASE_URL
import com.user.data.model.MediaItem

@Composable
fun MediaResultsScreen(
    mediaResults: Map<String, List<MediaItem>>,
    onItemClick: (MediaItem) -> Unit
) {
    if (mediaResults.isEmpty()) {
        Text("No results found", modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            mediaResults.entries.sortedBy { it.key }.forEach { (mediaType, mediaItems) ->
                item {
                    Text(
                        text = mediaType.uppercase(),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    Carousel(mediaItems, onItemClick)
                }
            }
        }
    }
}

@Composable
fun Carousel(mediaItems: List<MediaItem>, onItemClick: (MediaItem) -> Unit) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mediaItems) { mediaItem ->
            MediaCard(mediaItem, onItemClick)
        }
    }
}

@Composable
fun MediaCard(mediaItem: MediaItem, onItemClick: (MediaItem) -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(225.dp)
            .clickable { onItemClick(mediaItem) }
    ) {
        Image(
            painter = rememberAsyncImagePainter("$IMAGE_BASE_URL${mediaItem.posterPath}"),
            contentDescription = mediaItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
