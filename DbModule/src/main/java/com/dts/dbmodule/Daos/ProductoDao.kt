package com.dts.dbmodule.Daos

import androidx.room.*
import com.dts.dbmodule.Entities.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM Producto")
    suspend fun getAllProductos(): List<Producto>

    @Query("SELECT * FROM Producto WHERE status = :status")
    suspend fun getProductosByStatus(status: String): List<Producto>

    @Query("SELECT * FROM Producto WHERE id = :id")
    suspend fun getProductoById(id: Int): Producto

    @Insert()
    suspend fun insert(producto: Producto)

    @Update()
    suspend fun  update(producto: Producto)

    @Delete()
    suspend fun delete(producto: Producto)
}