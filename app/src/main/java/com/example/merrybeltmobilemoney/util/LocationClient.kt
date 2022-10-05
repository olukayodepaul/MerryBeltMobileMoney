package com.example.merrybeltmobilemoney.util


import android.location.Location
import kotlinx.coroutines.flow.Flow

//Abstraction
//write the implementation of the getLocationUpdate
interface LocationClient {
    fun getLocationUpdate(interval: Long) : Flow<Location>
    class LocationException(message: String): Exception()
}