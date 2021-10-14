package com.dts.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.dts.crud.databinding.MainActivityBinding
import com.dts.crud.ui.CreateUpdate.CreateUpdateFragment
import com.dts.crud.ui.main.MainFragment
import com.dts.dbmodule.Providers.DbProvider
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

    private lateinit var _binding : MainActivityBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        DbProvider.init(applicationContext)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}