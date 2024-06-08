package com.neatroots.agecalculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etBirthDate: EditText = findViewById(R.id.etBirthDate)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)
        val tvAgeResult: TextView = findViewById(R.id.tvAgeResult)

        btnCalculate.setOnClickListener {
            val birthDateString = etBirthDate.text.toString()
            if (birthDateString.isNotEmpty()) {
                val age = calculateAge(birthDateString)
                tvAgeResult.text = "Your Age is: $age years"
            } else {
                tvAgeResult.text = "Please enter your birth date"
            }
        }
    }

    private fun calculateAge(birthDateString: String): Int {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val birthDate = sdf.parse(birthDateString)
        val today = Calendar.getInstance().time

        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = birthDate

        val todayCalendar = Calendar.getInstance()
        todayCalendar.time = today

        var age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

        if (todayCalendar.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }
}
