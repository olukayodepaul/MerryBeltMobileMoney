package com.example.merrybeltmobilemoney.provider.room.room_provider_domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.merrybeltmobilemoney.provider.room.room_provider_data.Login


@Database(
  entities = [Login::class], version = 1, exportSchema = false
)
abstract class MerryBeltRoomDatabase: RoomDatabase() {
    abstract val doa: MerryBeltRoomDao

    companion object{
        val DATABASE_NAME_MERRYBELT = "com.example.merybeltmobileapp"
    }
}