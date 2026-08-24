package com.builtinmedia.hris.features.auth.di

import com.builtinmedia.hris.features.auth.data.datasource.AuthRemoteDataSource
import com.builtinmedia.hris.features.auth.data.repositories.AuthRepositoriesImpl
import com.builtinmedia.hris.features.auth.domain.repositories.AuthRepositories
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepositories(impl: AuthRepositoriesImpl): AuthRepositories

    companion object {
        @Provides
        @Singleton
        fun provideAuthRemoteDataSource(retrofit: Retrofit): AuthRemoteDataSource = retrofit.create(
            AuthRemoteDataSource::class.java
        )
    }
}