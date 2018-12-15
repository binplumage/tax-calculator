package com.ruru.taxcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    internal lateinit var computeButton:Button
    internal lateinit var clearButton:Button
    internal lateinit var tax:TextView
    internal lateinit var taxExcluedText:TextView
    internal lateinit var displayNumber:TextView
    internal lateinit var chineseNumberText:TextView
    internal lateinit var chineseNumberList:Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        computeButton = findViewById<Button>(R.id.compute)
        clearButton = findViewById<Button>(R.id.clearButton)
        tax = findViewById<TextView>(R.id.taxValue)
        taxExcluedText = findViewById<TextView>(R.id.taxExcludedValue)
        displayNumber = findViewById<TextView>(R.id.inputValue)
        chineseNumberText = findViewById<TextView>(R.id.chineseNumberValue)
        chineseNumberList = resources.getStringArray(R.array.number_list)

        computeButton.setOnClickListener {
                view-> calculateTax()
        }
    }

    fun calculateTax() {
        var value = displayNumber.text.toString().toFloat()
        if (value != 0.toFloat()) {
            var taxExcluded = (value / 1.05).roundToInt()
            var chineseNumberTmp = arrayListOf<String>()
            tax.text = (value - taxExcluded).roundToInt().toString()
            taxExcluedText.text = taxExcluded.toString()

            for (n in value.toInt().toString().toCharArray()) {
                var tmp = chineseNumberList[n.toString().toInt()]
                chineseNumberTmp.add(tmp)
            }
            chineseNumberText.text = chineseNumberTmp.joinToString("")
        }

    }

    fun showNum (view: View) {
        var numberButton = findViewById<Button>(view.id).text.toString()
        var nowTotalNumber = displayNumber.text.toString()
        if (nowTotalNumber == '0'.toString()) {
            displayNumber.text = numberButton
        }
        else {
            displayNumber.text = nowTotalNumber + numberButton
        }
    }

    fun clearNumber(view: View){
        displayNumber.text = "0"
        tax.text = "0"
        taxExcluedText.text = "0"
    }
}