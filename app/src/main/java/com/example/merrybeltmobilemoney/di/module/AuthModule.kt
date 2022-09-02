package com.example.merrybeltmobilemoney.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    @Named("TOKEN_KEY")
    fun authToken(): String {
        return "6a7941634d54705a4d6a346a53714461347274664d42475048"
    }

    @Provides
    @Singleton
    @Named("API_USER_LOGIN")
    fun apiUser() : String {
        return "external-user"
    }

    @Provides
    @Singleton
    fun apiID() : Int {
        return 8
    }

    @Provides
    @Singleton
    @Named("SERIAL_NUMBER")
    fun userSerialNumber() :String {
        return "63201125995137"
    }

    @Provides
    @Singleton
    @Named("STAN")
    fun userStan() :String {
        return "123456"
    }

    @Provides
    @Singleton
    @Named("ONLY_ACCOUNT_INFO")
    fun userOnlyAccountInfo() :Boolean {
        return false
    }

}