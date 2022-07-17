package com.example.merrybeltmobilemoney.di.module

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.MerryBeltPrefRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): MerryBeltPrefRepositoryImpl {
        return MerryBeltPrefRepositoryImpl(sharedPreferences)
    }

}