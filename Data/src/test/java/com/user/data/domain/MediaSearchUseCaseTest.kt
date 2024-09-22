package com.user.data.domain

import com.user.data.model.ApiResult
import com.user.data.model.MediaResponse
import com.user.data.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MediaSearchUseCaseTest {
    private lateinit var mediaSearchUseCase: MediaSearchUseCase
    private val mediaRepository: MediaRepository = mockk()

    @Before
    fun setUp() {
        mediaSearchUseCase = MediaSearchUseCase(mediaRepository)
    }

    @Test
    fun mediaSearchUseCaseSuccess() = runTest {
        // I am using Mockk, for mocking API response
        val mockResponse: MediaResponse = mockk()
        coEvery { mediaRepository.searchMedia(any()) } returns
                ApiResult.Success(mockResponse)

        val resultList = mediaSearchUseCase("test").toList()

        // Assertion
        assert(resultList.first() is ApiResult.Success)
        assertEquals((resultList.first() as ApiResult.Success).data, mockResponse)
    }

    @Test
    fun mediaSearchUseCaseFailure() = runTest {
        coEvery { mediaRepository.searchMedia(any()) } returns
                ApiResult.Error("Error occurred")

        val resultList = mediaSearchUseCase("test_query").toList()

        // Assertion
        assert(resultList.first() is ApiResult.Error)
        assertEquals((resultList.first() as ApiResult.Error).message, "Error occurred")
    }

}