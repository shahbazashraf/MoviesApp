package com.user.data.repository

import com.user.data.model.ApiResult
import com.user.data.model.MediaResponse
import com.user.data.network.MediaApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MediaRepositoryTest{
    private lateinit var mediaRepository: MediaRepositoryImpl
    private val mediaApiService: MediaApiService = mockk()

    @Before
    fun setUp() {
        mediaRepository = MediaRepositoryImpl(mediaApiService)
    }

    @Test
    fun searchMediaSuccessApiResponse() = runTest {
        val mockResponse: MediaResponse = mockk()
        coEvery { mediaApiService.searchMedia(any(), any()) } returns
                Response.success(mockResponse)
        // for testing i have added test as query
        val result = mediaRepository.searchMedia("test")

        // Assertion
        assert(result is ApiResult.Success)
        assertEquals((result as ApiResult.Success).data, mockResponse)
    }

    @Test
    fun searchMediaErrorApiResponse() = runTest {
        coEvery { mediaApiService.searchMedia(any(), any()) } returns
                Response.error(404, mockk(relaxed = true))

        val result = mediaRepository.searchMedia("test")

        // Assertion
        assert(result is ApiResult.Error)
        assertEquals((result as ApiResult.Error).message, "Response.error()")
    }

    @Test
    fun searchMediaApiException() = runTest {
        coEvery { mediaApiService.searchMedia(any(), any()) } throws
                Exception("Network Error")

        val result = mediaRepository.searchMedia("test")

        // Assertion
        assert(result is ApiResult.Error)
        assertEquals((result as ApiResult.Error).message, "Network Error")
    }
}