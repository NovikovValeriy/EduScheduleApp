package com.example.eduscheduleapp.di

import com.example.eduscheduleapp.common.Constants.BASE_URL
import com.example.eduscheduleapp.data.remote.EduScheduleApi
import com.example.eduscheduleapp.data.repository.EduScheduleRepositoryImpl
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEduScheduleApi(): EduScheduleApi{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideEduScheduleRepository(api : EduScheduleApi) : EduScheduleRepository{
        return EduScheduleRepositoryImpl(api)
    }
}