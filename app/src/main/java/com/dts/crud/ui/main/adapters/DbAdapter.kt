package com.dts.crud.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dts.crud.R
import com.dts.dbmodule.Entities.Producto
import kotlinx.android.synthetic.main.item_dbitem.view.*


class DbAdapter(var values: List<Producto>): RecyclerView.Adapter<DbAdapter.ItemHolder>(){

    class ItemHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(producto: Producto){
            view.itemName.text = producto.nombre
            view.itemDesc.text = producto.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(R.layout.item_dbitem,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.render(values[position])
    }

    override fun getItemCount(): Int = values.size
}