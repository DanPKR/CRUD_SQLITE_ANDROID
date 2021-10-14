package com.dts.crud.Domain

import com.dts.dbmodule.Entities.Producto
import com.dts.dbmodule.Enums.ProductStatus
import com.dts.dbmodule.Providers.DbProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object ProductosService {

    suspend fun createNewProduct(name: String, desc: String){
        return withContext(Dispatchers.IO){
            val new = Producto(
                id = null,
                nombre = name,
                descripcion = desc,
                createdAt = getCurrentTime(),
                updatedAt = getCurrentTime(),
                status = ProductStatus.ACTIVE.name
            )
            DbProvider.db!!.productoDao().insert(new)
        }
    }

    suspend fun findProduct(id: Int): Producto{
        return  withContext(Dispatchers.IO){
            val prod = DbProvider.db!!.productoDao().getProductoById(id)
            prod
        }
    }

    suspend fun updateProduct(id:Int, name: String, desc: String){
        return withContext(Dispatchers.IO){
            val prod = DbProvider.db!!.productoDao().getProductoById(id)
            prod.nombre = name
            prod.descripcion = desc
            prod.updatedAt = getCurrentTime()
            DbProvider.db!!.productoDao().update(prod)
        }
    }

    suspend fun getActiveProductos(): List<Producto>{
        return withContext(Dispatchers.IO){
            val list = DbProvider.db!!.productoDao().getProductosByStatus(ProductStatus.ACTIVE.name)
            list?: emptyList()
        }
    }

    suspend fun safeDeleteProduct(id: Int){
        return withContext(Dispatchers.IO){
            val prod = DbProvider.db!!.productoDao().getProductoById(id)
            prod.status = ProductStatus.INACTIVE.name
            prod.updatedAt = getCurrentTime()
            DbProvider.db!!.productoDao().update(prod)
        }
    }

    private fun getCurrentTime(): String{
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }
}