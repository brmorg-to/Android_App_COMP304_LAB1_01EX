package com.hfad.brunomorgado_comp304section002_lab01_ex01

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hfad.brunomorgado_comp304section002_lab01_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // observe the username and password LiveData and update the UI
        appViewModel.username.observe(this, Observer {})
        appViewModel.password.observe(this, Observer {})

        binding.loginButton.setOnClickListener {
            appViewModel.saveCredentials(binding.username.text.toString(), binding.password.text.toString())
            val values = arrayListOf<String>()
            appViewModel.username.value?.let { values.add(it) }
            appViewModel.password.value?.let { values.add(it) }
//            binding.username.text.toString()?.let { values.add(it) }
//            binding.password.text.toString()?.let { values.add(it) }
            var parcelableValues = values.toTypedArray()
            val intent = EmployeeDetails.newIntent(this@MainActivity, parcelableValues)
            startActivity(intent)

        }
    }
}