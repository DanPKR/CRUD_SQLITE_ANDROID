package com.dts.crud.ui.CreateUpdate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dts.crud.R
import com.dts.crud.databinding.CreateUpdateFragmentBinding

private const val ARG_PARAM1 = "id"

class CreateUpdateFragment : Fragment() {

    private var id: Int? = null

    companion object {

        fun newInstance(ID: Int) = CreateUpdateFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1,ID)
            }
        }

        fun newInstance() = CreateUpdateFragment()
    }

    private lateinit var viewModel: CreateUpdateViewModel
    private var _binding: CreateUpdateFragmentBinding? = null
    private val binding  get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateUpdateFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateUpdateViewModel::class.java)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }



}