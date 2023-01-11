package jibby.tutorials.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInputDisplay: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInputDisplay = findViewById(R.id.tvInputDisplay)
    }

    fun onDigit(view: View) {
        tvInputDisplay?.append((view as Button).text)
        Toast.makeText(this, "Button Clicked!", Toast.LENGTH_LONG).show()
    }

    fun onClear(view:View) {
        tvInputDisplay?.text = "0"
    }
}