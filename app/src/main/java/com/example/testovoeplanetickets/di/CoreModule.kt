package com.example.testovoeplanetickets.di

import android.content.Context
import com.example.testovoeplanetickets.cache.CacheDataManager
import com.example.testovoeplanetickets.network.api.ApiRoutes
import com.github.klee0kai.stone.annotations.module.Module
import com.github.klee0kai.stone.annotations.module.Provide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
open class CoreModule {

    @Provide(cache = Provide.CacheType.Strong)
    open fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provide(cache = Provide.CacheType.Factory)
    open fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiRoutes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
    }

    @Provide
    open fun provideCacheDataManager(context: Context): CacheDataManager {
        val sharedPreferences = context.getSharedPreferences("cache_data", Context.MODE_PRIVATE)
        return CacheDataManager(sharedPreferences)
    }
}