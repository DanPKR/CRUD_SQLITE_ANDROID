package com.dts.dbmodule.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var nombre: String,
    var descripcion: String,
    var status: String,
    val createdAt: String,
    var updatedAt: String
)