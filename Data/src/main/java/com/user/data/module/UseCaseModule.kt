package com.user.data.module

import com.user.data.domain.MediaSearchUseCase
import com.user.data.repository.MediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun mediaSearchUseCase(mediaRepository: MediaRepository):MediaSearchUseCase{
        return MediaSearchUseCase(mediaRepository)
    }
}