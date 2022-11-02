package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)//layout-generate/crete
        setContentView(binding.root)
        //binding.calculateButton.text="CALULATE"
        binding.apply {
            calculateButton.setOnClickListener {
                calculateTip()
            }
        }
    }
    @SuppressLint("StringFormatInvalid")
    private fun calculateTip(){
        val amt= binding.costOfService.text.toString().toDoubleOrNull()
        if (amt == null) {
            binding.tipAmount.text = "Tip Amount:"
            return
        }
        val tipPercentage=when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent->0.20
            R.id.option_eighteen_percent->0.18
            else->0.15
        }
        var tip=tipPercentage*amt
        val round=binding.roundUpSwitch.isChecked
        if(round){
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip= getCurrencyInstance().format(tip)

        binding.tipAmount.text=getString(R.string.tip_amount,formattedTip)
    }
}