package com.example.roomsample.di

import android.content.Context
import androidx.room.Room
import com.example.roomsample.data.AppDataBase
import com.example.roomsample.data.EmployeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideEmployeeDao(appDataBase: AppDataBase): EmployeeDao {
        return appDataBase.employeeDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "EmployeeDB"
        ).build()
    }
}