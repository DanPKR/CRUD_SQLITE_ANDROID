package com.dts.dbmodule.DataBases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dts.dbmodule.Daos.ProductoDao
import com.dts.dbmodule.Entities.Producto

@Database(
    entities = [Producto::class],
    version = 1
)
 abstract class DbProductos : RoomDatabase(){
    abstract fun productoDao(): ProductoDao
}