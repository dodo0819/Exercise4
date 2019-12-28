package com.example.exercise4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView_date)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)

            }

        textView.setOnClickListener {
            DatePickerDialog(
                this@MainActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        findViewById<Button>(R.id.ok).setOnClickListener(){
            getAge(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
        }
    }

    private fun getAge(year: Int, month: Int, day: Int): String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        val getAge = findViewById<TextView>(R.id.age)
        val getSaving = findViewById<TextView>(R.id.basicsaving)
        var saving = 0;

        dob.set(year, month, day)

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageInt = age

        if(ageInt >= 16 && ageInt <= 20){
            saving = 5000
        } else if (ageInt >= 21 && ageInt <= 25){
            saving = 14000
        } else if(ageInt >= 26 && ageInt <= 30){
            saving = 29000
        } else if(ageInt >= 31 && ageInt <= 35){
            saving = 50000
        } else if (ageInt >= 36 && ageInt <= 40){
            saving = 78000
        } else if(ageInt >= 41 && ageInt <= 45){
            saving = 116000
        } else if(ageInt >= 46 && ageInt <= 50){
            saving = 165000
        } else if(ageInt >= 51 && ageInt <= 55){
            saving = 228000
        }

        getAge.text = getString(R.string.your_age)+ ageInt.toString()
        getSaving.text = getString(R.string.basicsaving) + saving.toString()

        return ageInt.toString()
    }
}

