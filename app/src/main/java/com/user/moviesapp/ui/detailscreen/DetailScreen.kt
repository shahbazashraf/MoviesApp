package com.user.moviesapp.ui.detailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.user.data.constant.Constants.IMAGE_BASE_URL
import com.user.data.model.MediaItem
import com.user.moviesapp.R

@Composable
fun DetailScreen(mediaItem: MediaItem, onPlayClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter("$IMAGE_BASE_URL${mediaItem.posterPath}"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = mediaItem.title ?: mediaItem.name ?: "",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = mediaItem.overview.orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (mediaItem.mediaType == "movie" || mediaItem.mediaType == "tv") {
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    Text(text = stringResource(R.string.play))
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
