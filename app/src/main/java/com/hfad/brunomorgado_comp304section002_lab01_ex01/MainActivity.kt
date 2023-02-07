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
    //Use of the data binding library to create references to the layout views
    private lateinit var binding: ActivityMainBinding
    // Use of viewModels to store and manage UI-related data throughout the activity's lifecycle
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //Inflating activity_main.xml
        setContentView(binding.root)

        // observe the username and password LiveData and eventually update the UI
        appViewModel.username.observe(this, Observer {})
        appViewModel.password.observe(this, Observer {})

        //Set event listener to the login button
        binding.loginButton.setOnClickListener {
            // Method declare in AppViewModels to store login credentials
            appViewModel.saveCredentials(binding.username.text.toString(), binding.password.text.toString())
            val values = arrayListOf<String>()
            appViewModel.username.value?.let { values.add(it) }
            appViewModel.password.value?.let { values.add(it) }
            var parcelableValues = values.toTypedArray()
            //Instantiating and starting a new Intent to pass employee's data to pass data to EmployeeDetails activity
            val intent = EmployeeDetails.newIntent(this@MainActivity, parcelableValues)
            startActivity(intent)

        }
    }
}