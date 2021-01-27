package com.jemandandere.deeds.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jemandandere.deeds.App
import com.jemandandere.deeds.R
import com.jemandandere.deeds.databinding.ActivityMainBinding
import com.jemandandere.deeds.model.Deed

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        App.instance.navController = Navigation.findNavController(this, R.id.nav_controller)
        title = getString(R.string.app_name)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}