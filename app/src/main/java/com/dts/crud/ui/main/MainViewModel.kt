package com.dts.crud.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dts.crud.Domain.ProductosService
import com.dts.dbmodule.Entities.Producto
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    val data: MutableLiveData<List<Producto>> by lazy {
        MutableLiveData<List<Producto>>()
    }
    fun FetchData(){
        viewModelScope.launch {
            val values =  ProductosService.getActiveProductos()
            data.postValue(values)
        }
    }
}