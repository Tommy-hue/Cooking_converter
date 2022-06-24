package com.example.cookingconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookingconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener { conversionCalc() }
    }

     private fun conversionCalc() {
         //Get input from text field and store as String
         val stringInTextField = binding.amountInVolume.text.toString()
         val amount = stringInTextField.toDoubleOrNull()

         if (amount == null) {
             binding.conversionResult.text = ""
             return
         }

         if (binding.conversionOptions.checkedRadioButtonId == R.id.milli_oz) {
             var oz = milliToOz(amount)
             // if round up switch is checked
             if (binding.converterSwitch.isChecked) {
                 oz = kotlin.math.ceil(oz)
             }
             val milliToOzResult = "$amount ml equals to $oz oz"
             binding.conversionResult.text = milliToOzResult
         }
         else {
             var milli = ozToMilli(amount)
             if (binding.converterSwitch.isChecked) {
                 milli = kotlin.math.ceil(milli)
             }
             val ozToMilliResult = "$amount oz equals to $milli ml"
             binding.conversionResult.text = ozToMilliResult
         }
    }

    private fun milliToOz (amount: Double): Double {
           return amount.times(0.03381402)
        }

    private fun ozToMilli (amount: Double): Double {
        return amount.times(29.57353193)
    }
}
