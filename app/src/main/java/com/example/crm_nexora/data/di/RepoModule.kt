package com.example.crm_nexora.data.di

import com.example.crm_nexora.data.repo.AppSettingsDSImpl
import com.example.crm_nexora.data.repo.AuthRepoImpl
import com.example.crm_nexora.data.repo.AuthSessionLocalRepoImpl
import com.example.crm_nexora.data.repo.UserDSRepoImpl
import com.example.crm_nexora.domain.repo.AuthRepo
import com.example.crm_nexora.domain.repo.AuthSessionLocalRepo
import com.example.crm_nexora.domain.repo.local.AppSettingsRepo
import com.example.crm_nexora.domain.repo.local.UserLocalRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindUserLocalRepo(impl: UserDSRepoImpl): UserLocalRepo
    @Binds
    @Singleton
    abstract fun bindAppSettingsRepo(impl: AppSettingsDSImpl): AppSettingsRepo

    @Binds
    @Singleton
    abstract fun bindAuthRepo(impl: AuthRepoImpl): AuthRepo

    @Binds
    @Singleton
    abstract fun bindAuthSessionLocalRepo(impl: AuthSessionLocalRepoImpl): AuthSessionLocalRepo
}