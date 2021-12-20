package com.reachplc.interview.ui.list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reachplc.interview.data.remote.ProductsRepository
import com.reachplc.interview.data.remote.ProductsResponse
import com.reachplc.interview.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val app: Application
) :
    AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private var _resourceResponse = MutableLiveData<Resource<ProductsResponse>>()
    val resourceResponse = _resourceResponse
    private var _apiResponse = MutableLiveData<ProductsResponse>()

    fun getProducts() = viewModelScope.launch {
        _resourceResponse.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = repository.getProducts()
                _resourceResponse.postValue(Resource.Success(apiResult))
            } else {
                _resourceResponse.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            _resourceResponse.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}