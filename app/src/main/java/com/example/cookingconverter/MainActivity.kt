package com.example.cookingconverter

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener { conversionCalc() }

        binding.amountInVolumeEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun conversionCalc() {
        //Get input from text field and store as String
        val stringInTextField = binding.amountInVolumeEditText.text.toString()
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
            binding.conversionResult.text = getString(R.string.converted_result, milliToOzResult)
        } else {
            var milli = ozToMilli(amount)
            if (binding.converterSwitch.isChecked) {
                milli = kotlin.math.ceil(milli)
            }
            val ozToMilliResult = "$amount oz equals to $milli ml"
            binding.conversionResult.text = getString(R.string.converted_result, ozToMilliResult)
        }
    }

    private fun milliToOz(amount: Double): Double {
        return amount.times(0.03381402)
    }

    private fun ozToMilli(amount: Double): Double {
        return amount.times(29.57353193)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
