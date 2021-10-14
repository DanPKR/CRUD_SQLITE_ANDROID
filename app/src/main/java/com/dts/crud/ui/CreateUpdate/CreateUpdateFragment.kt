package com.dts.crud.ui.CreateUpdate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.dts.crud.R
import com.dts.crud.databinding.CreateUpdateFragmentBinding
import com.dts.crud.ui.main.MainFragment
import com.dts.dbmodule.Entities.Producto

private const val ARG_PARAM1 = "id"
private const val TAG = "CreateUpdateFragment"

class CreateUpdateFragment : Fragment(), View.OnClickListener {

    private var id: Int? = null

    companion object {
        // Si queremos actualizar un registro mandamos el id al fragment
        fun newInstance(ID: Int) = CreateUpdateFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1,ID)
            }
        }

        // Si queremos crear un nuevo registro no se requiere id
        fun newInstance() = CreateUpdateFragment()
    }

    private lateinit var viewModel: CreateUpdateViewModel
    private var _binding: CreateUpdateFragmentBinding? = null
    private val binding  get() = _binding!!


   // View Binding para facilitar el control de los elementos en la interfaz
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateUpdateFragmentBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateUpdateViewModel::class.java)
        //Si llega un Id al fragment significa que queremos actualizar un registro existente
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
            Log.d(TAG, "onCreate: $id")
            viewModel.currentProducto.observe(this,{
                FillFields(it)
            })
            viewModel.GetCurrentProductoInfo(id!!)
        }
    }

    // Funcion para inicializar views
    private fun initViews(){
        binding.savebtn.setOnClickListener(this)
        binding.cancelbtn.setOnClickListener(this)
        binding.NombreTIL.editText!!.doOnTextChanged { text, start, before, count ->
        binding.savebtn.isEnabled = !text.isNullOrBlank() && !binding.DescripccionTIL.editText!!.text.isNullOrBlank()
        }
        binding.DescripccionTIL.editText!!.doOnTextChanged { text, start, before, count ->
            binding.savebtn.isEnabled = !binding.NombreTIL.editText!!.text.isNullOrBlank() && !text.isNullOrBlank()
        }
    }

    //Funcion para mostrar info de un registro existente
    private fun FillFields(producto: Producto){
        binding.title.setText("Actualizar Registro")
        binding.NombreTIL.editText?.setText(producto.nombre)
        binding.DescripccionTIL.editText?.setText(producto.descripcion)
    }

    private fun onSaveClicked(){
        Log.d(TAG, "onClick: Save")
        viewModel.SaveData(
            binding.NombreTIL.editText!!.text.toString(),
            binding.DescripccionTIL.editText!!.text.toString(),
            null
        )
        goToMain()
    }

    private fun onCancelClicked(){
        Log.d(TAG, "onClick: Cancel")
        goToMain()
    }

    private fun goToMain(){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container,MainFragment.newInstance())
            .commit()
    }


    // Un solo listener para todas las views y discriminamos su comportamiento
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.savebtn -> onSaveClicked()
            R.id.cancelbtn -> onCancelClicked()
        }
    }


}