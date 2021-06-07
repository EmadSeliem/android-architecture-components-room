package com.example.roomsample.di

import com.example.roomsample.adapters.EmployeeListAdapter
import com.example.roomsample.data.Employee
import com.example.roomsample.util.NavigationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class AdapterModule {

    @Provides
    fun provideEmployeeListAdapter(navigationHelper: NavigationHelper): EmployeeListAdapter {
        return EmployeeListAdapter(navigationHelper)
    }

}