package com.user.data.repository

import com.user.data.model.ApiResult
import com.user.data.model.MediaResponse

interface MediaRepository {
    suspend fun searchMedia(query: String): ApiResult<MediaResponse>
}