package com.qxdzbc.pcr.di

import android.content.Context
import com.qxdzbc.pcr.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SystemModule {

    companion object{
        @Provides
        fun app(@ApplicationContext context:Context):App{
            return context as App
        }
    }
}
