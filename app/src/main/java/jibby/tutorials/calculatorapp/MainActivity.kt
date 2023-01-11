package jibby.tutorials.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.ArtistColumns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInputDisplay: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInputDisplay = findViewById(R.id.tvInputDisplay)
    }

    fun onDigit(view: View) {
        tvInputDisplay?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view:View) {
        tvInputDisplay?.text = ""
    }

    fun onDecimal(view: View) {
        if(tvInputDisplay?.text.toString().contains(".")) {
            return
        }
        if (lastNumeric && !lastDot) {
            tvInputDisplay?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        // if tvInputDisplay exists and if it also has a text inside it -- execute the code below
        tvInputDisplay?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())) {
                tvInputDisplay?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onEquals(view: View) {
        if(lastNumeric) {
            var tvValue = tvInputDisplay?.text.toString()
            var prefix = ""
            try {

                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    var result = (one.toDouble() - two.toDouble())
                    tvInputDisplay?.text = removeZeroAfterDecimal(result.toString())
                } else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    var result = (one.toDouble() + two.toDouble())
                    tvInputDisplay?.text = removeZeroAfterDecimal(result.toString())
                } else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    var result = (one.toDouble() / two.toDouble())
                    tvInputDisplay?.text = removeZeroAfterDecimal(result.toString())
                } else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    var result = (one.toDouble() * two.toDouble())
                    tvInputDisplay?.text = removeZeroAfterDecimal(result.toString())
                }
            } catch(e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDecimal(result: String): String {
        var value = result
        if(value.endsWith(".0")) {
            value = result.substring(0, result.length-2)
        }
        return value
    }
}