package com.dts.crud.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dts.crud.R
import com.dts.crud.databinding.MainFragmentBinding
import com.dts.crud.ui.CreateUpdate.CreateUpdateFragment
import com.dts.crud.ui.main.adapters.DbAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), View.OnClickListener {
    private val TAG = "MainFragment"
    private val adapter = DbAdapter(emptyList())

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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        _binding = MainFragmentBinding.inflate(layoutInflater,container,false)
        initViews()
        return binding.root
    }
    private fun initViews(){
        binding.addFB.setOnClickListener(this)
        binding.DbElementsRV.layoutManager = LinearLayoutManager(activity)
        binding.DbElementsRV.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner,{
            adapter.values = it
        })
        viewModel.FetchData()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.addFB->{
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,CreateUpdateFragment.newInstance())
                    .commit()
            }
        }
    }

}