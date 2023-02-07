package com.hfad.brunomorgado_comp304section002_lab01_ex01

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.hfad.brunomorgado_comp304section002_lab01_ex01.databinding.ActivityEmployeeDetailsBinding
import java.text.DecimalFormat

private const val EMPLOYEE_NAME = "com.hfad.brunomorgado_COMP304Section002_Lab01_Ex01.username"

class EmployeeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeDetailsBinding
    private lateinit var employee: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Extract employee's username from Intent
        employee = intent.getStringExtra(EMPLOYEE_NAME).toString()

        binding.employeeName.setText(employee)

        var income: Double = 0.0
        var netIncome: Double = 0.0
        var tax: Double = 0.00

        // Set Event Listener to the Calculate button
        binding.calculateButton.setOnClickListener{

            val salary = binding.salary.text.toString().trim()
            val taxRate = binding.taxRate.text.toString().trim()

            //Validate if salary or tax rate are empty. If true, return required field message
            if(salary.isEmpty() || taxRate.isEmpty()){
                binding.salary.error = "This field is required"
                binding.taxRate.error = "This field is required"
            }

            //If salary and tax rate are populated, make conversion to double and necessary adjusts and perform netIncome calculation
            try {
                income = salary.toDouble()
            }catch (e: java.lang.NumberFormatException) {
                Toast.makeText(this, "Invalid salary input.", Toast.LENGTH_LONG).show()
            }

            try {
                tax = taxRate.toDouble() / 100.00
            }catch (e: java.lang.NumberFormatException) {
                Toast.makeText(this, "Invalid tax input.", Toast.LENGTH_LONG).show()
            }

            try{
                netIncome = income * (1 - tax)
            }catch (e: Exception){
                Toast.makeText(this, "Please check your inputs. Calculation error!", Toast.LENGTH_LONG).show()
            }

            if(!netIncome.isNaN()) {
                val numberFormat = DecimalFormat("#,###.##")
                val formattedNumber = numberFormat.format(netIncome)
                val message = "Hi $employee, Your net income is: $$formattedNumber"
                binding.summary.setText(message)
            }
        }

    }

    companion object {
        fun newIntent(packageContext: Context, employeeData: Array<String>): Intent {
            return Intent(packageContext, EmployeeDetails::class.java).apply {
                putExtra(EMPLOYEE_NAME, employeeData[0])
            }
        }
    }
}