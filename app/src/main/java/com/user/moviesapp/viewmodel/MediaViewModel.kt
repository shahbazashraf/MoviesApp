package com.user.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.data.domain.MediaSearchUseCase
import com.user.data.model.ApiResult
import com.user.data.model.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val mediaSearchUseCase: MediaSearchUseCase
) : ViewModel() {

    private val _mediaItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val mediaItems: StateFlow<Map<String, List<MediaItem>>> = _mediaItems
        .map { items ->
            items.groupBy { it.mediaType }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyMap())

    // We can use this to display any loading indicator, haven't implemented yet
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // We can use this to display error e.g. dialog, haven't implemented yet
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun searchMedia(query: String) {
        viewModelScope.launch {
            _loading.value = true
            mediaSearchUseCase(query).collect { result ->
                _loading.value = false
                when (result) {
                    is ApiResult.Success -> {
                        _mediaItems.value = result.data?.results?: emptyList()
                    }
                    is ApiResult.Error -> {
                        _error.value = result.message ?: "An unknown error occurred"
                    }
                }
            }
        }
    }
}
