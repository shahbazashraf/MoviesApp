package com.user.data.domain

import com.user.data.model.ApiResult
import com.user.data.model.MediaResponse
import com.user.data.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MediaSearchUseCase(
    private val mediaRepository: MediaRepository
) {
    suspend operator fun invoke(query: String): Flow<ApiResult<MediaResponse>> {
        return flowOf(mediaRepository.searchMedia(query))
    }
}