package com.user.data.repository

import com.user.data.constant.Constants.API_KEY
import com.user.data.model.ApiResult
import com.user.data.model.MediaResponse
import com.user.data.network.MediaApiService
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : MediaRepository {

    override suspend fun searchMedia(query: String): ApiResult<MediaResponse> {
        return try {
            val response = mediaApiService.searchMedia(API_KEY, query)
            if (response.isSuccessful) {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResult.Error(e.message, e.cause)
        }
    }
}
