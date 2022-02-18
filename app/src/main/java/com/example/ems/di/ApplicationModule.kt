package com.example.roomdatabaseandroid.di

import android.content.Context
import com.example.ems.data.local.EmsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideService(@ApplicationContext context: Context):EmsDataBase=EmsDataBase.getDatabaseInstance(context)

}
