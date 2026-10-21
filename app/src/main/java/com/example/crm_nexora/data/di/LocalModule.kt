package com.example.crm_nexora.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.example.crm_nexora.data.local.crypto.CryptoManager
import com.example.crm_nexora.data.local.datastore.model.AppSettingsDS
import com.example.crm_nexora.data.local.datastore.model.AuthSessionDS
import com.example.crm_nexora.data.local.datastore.model.UserDS
import com.example.crm_nexora.data.local.datastore.serializer.AppSettingsDSSerializer
import com.example.crm_nexora.data.local.datastore.serializer.AuthSessionDSSerializer
import com.example.crm_nexora.data.local.datastore.serializer.UserDSSerializer
import com.example.crm_nexora.data.di.annotation.LocalStorageJson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

private const val DATA_STORE_USER_INFO = "userInfo.pb"
private const val DATA_STORE_APP_SETTINGS = "appSettings.pb"
private const val AUTH_SESSION_FILE = "auth_session.pb"

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {


    @Provides
    @Singleton
    @LocalStorageJson
    fun provideLocalJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun provideUserInfoProtoDataStore(
        @ApplicationContext appContext: Context,
        @LocalStorageJson json: Json
    ): DataStore<UserDS> {
        return DataStoreFactory.create(
            serializer = UserDSSerializer(json),
            produceFile = { appContext.dataStoreFile(DATA_STORE_USER_INFO) },
            corruptionHandler = ReplaceFileCorruptionHandler { UserDS() },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

    @Singleton
    @Provides
    fun provideAppSettingsProtoDataStore(
        @ApplicationContext appContext: Context,
        @LocalStorageJson json: Json
    ): DataStore<AppSettingsDS> {
        return DataStoreFactory.create(
            serializer = AppSettingsDSSerializer(json),
            produceFile = { appContext.dataStoreFile(DATA_STORE_APP_SETTINGS) },
            corruptionHandler = ReplaceFileCorruptionHandler { AppSettingsDS() },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

    @Provides
    @Singleton
    fun provideAuthSessionDataStore(
        @ApplicationContext context: Context,
        cryptoManager: CryptoManager,
        @LocalStorageJson json: Json
    ): DataStore<AuthSessionDS> {
        return DataStoreFactory.create(
            serializer = AuthSessionDSSerializer(cryptoManager, json),
            produceFile = { context.dataStoreFile(AUTH_SESSION_FILE) },
            corruptionHandler = ReplaceFileCorruptionHandler { AuthSessionDS() },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

    @Provides
    @Singleton
    fun provideCryptoManager(): CryptoManager = CryptoManager()
}