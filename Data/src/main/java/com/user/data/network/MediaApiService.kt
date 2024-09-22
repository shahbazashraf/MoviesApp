package com.user.data.network

import com.user.data.model.MediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApiService {

    @GET("search/multi")
    suspend fun searchMedia(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<MediaResponse>
}
