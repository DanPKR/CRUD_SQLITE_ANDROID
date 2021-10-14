package com.dts.dbmodule.Providers

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dts.dbmodule.DataBases.DbProductos

object DbProvider {
    var db: DbProductos? = null
    get() {
        if (field == null)
            //Si el modulo no fue inicializado arrojamos una excepcion
            throw Exception("Db Module Not Initialized")
        return field
    }

    //Incializamos el Modulo
    fun init(ctx: Context){
        db = Room.databaseBuilder(ctx,
        DbProductos::class.java,
        "DbProductos").build()
    }
}