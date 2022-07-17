package com.example.merrybeltmobilemoney.di.module

import androidx.room.Room
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDao
import com.example.merrybeltmobilemoney.provider.room.room_provider_domain.MerryBeltRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Dao{

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MerryBeltRoomDatabase {
        return Room.databaseBuilder(
            app, MerryBeltRoomDatabase::class.java, MerryBeltRoomDatabase.DATABASE_NAME_MERRYBELT
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: MerryBeltRoomDatabase): MerryBeltRoomDao {
        return db.doa
    }

}
