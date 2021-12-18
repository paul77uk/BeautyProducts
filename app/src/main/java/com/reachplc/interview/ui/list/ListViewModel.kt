package com.reachplc.interview.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachplc.interview.data.remote.ProductsRepository
import com.reachplc.interview.data.remote.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel
    private var _liveData = MutableLiveData<ProductsResponse>()
    val liveData = _liveData

    fun getProducts() = viewModelScope.launch {
        val api = repository.getProducts()
        _liveData.value = api
    }

}