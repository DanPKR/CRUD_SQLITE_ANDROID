package com.dts.dbmodule.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String
)