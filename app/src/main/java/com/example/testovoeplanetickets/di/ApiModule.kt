package com.example.testovoeplanetickets.di

import com.example.testovoeplanetickets.network.api.MainMenuApi
import com.github.klee0kai.stone.annotations.module.BindInstance
import com.github.klee0kai.stone.annotations.module.Module
import com.github.klee0kai.stone.annotations.module.Provide
import retrofit2.Retrofit


@Module
interface ApiModule {

    @BindInstance(cache = BindInstance.CacheType.Strong)
    fun retrofit(r: Retrofit? = null): Retrofit

    @Provide(cache = Provide.CacheType.Soft)
    fun provideMainMenuApi(retrofit: Retrofit = this.retrofit() ) : MainMenuApi
}