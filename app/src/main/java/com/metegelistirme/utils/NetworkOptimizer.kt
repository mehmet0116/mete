package com.metegelistirme.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

object NetworkOptimizer {
    
    private const val TAG = "NetworkOptimizer"
    
    /**
     * Check network connectivity
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected == true
        }
    }
    
    /**
     * Get network type
     */
    fun getNetworkType(context: Context): NetworkType {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            
            when {
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> NetworkType.WIFI
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> NetworkType.CELLULAR
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> NetworkType.ETHERNET
                else -> NetworkType.UNKNOWN
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            
            when (networkInfo?.type) {
                ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                ConnectivityManager.TYPE_MOBILE -> NetworkType.CELLULAR
                ConnectivityManager.TYPE_ETHERNET -> NetworkType.ETHERNET
                else -> NetworkType.UNKNOWN
            }
        }
    }
    
    /**
     * Monitor network connectivity changes
     */
    fun monitorNetworkConnectivity(context: Context): Flow<Boolean> = flow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        // Initial state
        emit(isNetworkAvailable(context))
        
        // Note: For continuous monitoring, use ConnectivityManager.NetworkCallback
        // This is a simplified version
    }
    
    /**
     * Optimize data fetching based on network type
     */
    fun getDataFetchStrategy(networkType: NetworkType): DataFetchStrategy {
        return when (networkType) {
            NetworkType.WIFI -> DataFetchStrategy(
                prefetchEnabled = true,
                cacheDuration = 3600, // 1 hour
                imageQuality = ImageQuality.HIGH,
                batchSize = 50
            )
            NetworkType.CELLULAR -> DataFetchStrategy(
                prefetchEnabled = false,
                cacheDuration = 1800, // 30 minutes
                imageQuality = ImageQuality.MEDIUM,
                batchSize = 20
            )
            else -> DataFetchStrategy(
                prefetchEnabled = false,
                cacheDuration = 900, // 15 minutes
                imageQuality = ImageQuality.LOW,
                batchSize = 10
            )
        }
    }
    
    data class DataFetchStrategy(
        val prefetchEnabled: Boolean,
        val cacheDuration: Int, // seconds
        val imageQuality: ImageQuality,
        val batchSize: Int
    )
    
    enum class NetworkType {
        WIFI, CELLULAR, ETHERNET, UNKNOWN
    }
    
    enum class ImageQuality {
        LOW, MEDIUM, HIGH
    }
}