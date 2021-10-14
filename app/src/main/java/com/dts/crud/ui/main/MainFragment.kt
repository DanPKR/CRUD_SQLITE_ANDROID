package com.dts.crud.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dts.crud.R
import com.dts.crud.databinding.MainFragmentBinding
import com.dts.crud.ui.CreateUpdate.CreateUpdateFragment

class MainFragment : Fragment(), View.OnClickListener {
    private val TAG = "MainFragment"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private  var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater,container,false)
        initViews()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initViews(){
        binding.addFB.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.addFB->{
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,CreateUpdateFragment.newInstance())
                    .commit()
            }
        }
    }

}