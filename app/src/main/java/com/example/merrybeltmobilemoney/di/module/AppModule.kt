package com.example.merrybeltmobilemoney.di.module


import android.content.SharedPreferences
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.LoginApi
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.MerryBeltApi
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.MerryBeltApiRepositoryImpl
import com.example.merrybeltmobilemoney.provider.api.api_provider_data.MerryBeltEncryptedApi
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_data.MerryBeltPrefRepositoryImpl
import com.example.merrybeltmobilemoney.provider.preference.pref_provider_domain.MerryBeltPrefRepository
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMerryBeltPrefRepository(
        sharedPref: SharedPreferences
    ): MerryBeltPrefRepository {
        return MerryBeltPrefRepositoryImpl(
            sharedPref
        )
    }

    @Provides
    @Singleton
    fun provideMerrybeltRepository(
        merryBeltApi: MerryBeltApi,
        merryBeltEncryptedApi: MerryBeltEncryptedApi,
        loginApi: LoginApi,
        @Named("TOKEN_KEY") authToken: String,
        @Named("API_USER_LOGIN") apiUser: String,
        apiId: Int,
        merryBeltRoomDao: MerryBeltRoomDao,
        merryPref: MerryBeltPrefRepository,
        @Named("SERIAL_NUMBER") userSerialNumber: String,
        @Named("STAN") userStan: String,
        @Named("ONLY_ACCOUNT_INFO") userOnlyAccountInfo: Boolean
    ): MerryBeltApiRepository {
        return MerryBeltApiRepositoryImpl(
            merryBeltApi, merryBeltEncryptedApi, loginApi, authToken, apiUser, apiId, merryBeltRoomDao, merryPref, userSerialNumber, userStan, userOnlyAccountInfo
        )
    }



}