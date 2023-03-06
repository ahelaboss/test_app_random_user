package com.yourgains.testapprandomuser.di

import com.yourgains.testapprandomuser.data.repository.UserRepositoryImpl
import com.yourgains.testapprandomuser.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): IUserRepository
}