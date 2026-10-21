package com.example.crm_nexora.data.di

import android.R.attr.level
import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.crm_nexora.BuildConfig
import com.example.crm_nexora.data.di.annotation.RefreshClient
import com.example.crm_nexora.data.local.AppSettingsManager
import com.example.crm_nexora.data.local.session.AuthSessionManager
import com.example.crm_nexora.data.remote.interceptor.AccountDeactivatedInterceptor
import com.example.crm_nexora.data.remote.interceptor.AuthInterceptor
import com.example.crm_nexora.data.remote.interceptor.ClientInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
//
//    @Singleton
//    @Provides
//    fun providesOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor,
//        appSettingsManager: AppSettingsManager,
//        authSessionManager: AuthSessionManager,
//        json: Json,
//        @RefreshClient authService: AuthService,
//        @ApplicationContext context: Context
//    ): OkHttpClient {
//        @SuppressLint("HardwareIds")
//        val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
//
//        val builder = OkHttpClient.Builder()
//            .readTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
//
//            .addInterceptor(AuthInterceptor(authSessionManager))
//            .addInterceptor(ClientInterceptor(appSettingsManager, deviceId))
//            .addInterceptor(AccountDeactivatedInterceptor(authSessionManager, json))
//      //      .authenticator(TokenAuthenticator(authSessionManager, authService))
//
//        //  .addInterceptor(NetworkConnectionInterceptor(context))
//        if (BuildConfig.DEBUG) {
//
//            builder.addInterceptor(httpLoggingInterceptor)
//            builder.addInterceptor(ChuckerInterceptor.Builder(context).build())
//        }
//
//        return builder.build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
//        val contentType = "application/json".toMediaType()
//
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .client(okHttpClient)
//            .build()
//    }
//

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }


}
