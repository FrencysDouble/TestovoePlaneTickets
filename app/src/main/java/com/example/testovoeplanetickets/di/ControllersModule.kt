package com.example.testovoeplanetickets.di

import android.content.Context
import com.example.testovoeplanetickets.cache.CacheDataManager
import com.example.testovoeplanetickets.controllers.CalendarController
import com.example.testovoeplanetickets.controllers.PlaneScreenController
import com.example.testovoeplanetickets.controllers.SearchScreenController
import com.example.testovoeplanetickets.controllers.SelectedText
import com.example.testovoeplanetickets.network.MainApiController
import com.example.testovoeplanetickets.network.MainMenuApiInterface
import com.github.klee0kai.stone.annotations.module.BindInstance
import com.github.klee0kai.stone.annotations.module.Module
import com.github.klee0kai.stone.annotations.module.Provide


@Module
interface ControllersModule {

    @BindInstance
    fun mApiController(ma : MainApiController? = null) : MainApiController


    @Provide(cache = Provide.CacheType.Soft)
    fun providePlaneController(cacheDataManager : CacheDataManager = provideCacheManager(),mainMenuApiInterface: MainMenuApiInterface = mApiController()) : PlaneScreenController

    @BindInstance
    fun provideCacheManager(cacheDataManager: CacheDataManager? = null) : CacheDataManager

    @Provide(cache = Provide.CacheType.Soft)
    fun provideSelectedText(cacheDataManager: CacheDataManager? = provideCacheManager()) : SelectedText

    @Provide(cache = Provide.CacheType.Soft)
    fun provideCalendarController(context: Context? = null) : CalendarController

    @Provide(cache = Provide.CacheType.Soft)
    fun provideSearchScreenController(mainMenuApiInterface: MainMenuApiInterface = mApiController()) : SearchScreenController
}