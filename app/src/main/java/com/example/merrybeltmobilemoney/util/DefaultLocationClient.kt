package com.example.merrybeltmobilemoney.util

import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationClient {

    override fun getLocationUpdate(interval: Long): Flow<Location> {
        return callbackFlow {

            if(!context.hasLocationPermission()){
                throw LocationClient.LocationException("Mission location permission")
            }

            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if(!isGPSEnable && !isNetworkEnable){
                throw  LocationClient.LocationException("GPS is disable")
            }


        }
    }
}