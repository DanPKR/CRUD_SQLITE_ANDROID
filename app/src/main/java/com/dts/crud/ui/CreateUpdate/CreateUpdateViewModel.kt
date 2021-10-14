package com.dts.crud.ui.CreateUpdate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dts.crud.Domain.ProductosService
import com.dts.dbmodule.Entities.Producto
import kotlinx.coroutines.launch

private const val TAG = "CreateUpdateViewModel"

private enum class FragmentMode{
    UPDATE,CREATENEW
}

class CreateUpdateViewModel : ViewModel() {
    //El modo por default del Fragment es crear un nuevo registro
    private var currentMode: FragmentMode = FragmentMode.CREATENEW
    val currentProducto: MutableLiveData<Producto> by lazy {
        MutableLiveData<Producto>()
    }

    val taskResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun GetCurrentProductoInfo(id: Int){
        currentMode = FragmentMode.UPDATE
        viewModelScope.launch {
            val prod = ProductosService.findProduct(id)
            currentProducto.postValue(prod)
        }
    }

    fun SaveData(nombre: String, desc: String, id:Int?){
        viewModelScope.launch {
            when(currentMode){
                FragmentMode.UPDATE -> ProductosService.updateProduct(currentProducto.value?.id!!,nombre,desc)
                FragmentMode.CREATENEW -> ProductosService.createNewProduct(nombre,desc)
            }
        }
    }

}