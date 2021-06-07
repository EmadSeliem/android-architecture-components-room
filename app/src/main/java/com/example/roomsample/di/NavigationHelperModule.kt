package com.example.roomsample.di

import com.example.roomsample.util.NavigationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationHelperModule {

    @Singleton
    @Provides
    fun provideNavigationHelper(): NavigationHelper {
        return NavigationHelper()
    }
}