package com.nayan.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            callDatePickerDialog(view)
        }
    }

    /*
    After getting the difference of dates in MilliSeconds divide it by below variables to get the respective values.
    val seconds = 1000
    val minutes = 1000 * 60
    val hours = 1000 * 60 * 60
    val days = 1000 * 60 * 60 * 24
    */
    private fun callDatePickerDialog(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The chosen year is $selectedYear, The month is $selectedMonth and The day is $selectedDayOfMonth", Toast.LENGTH_SHORT).show()

            val selectedDate = "${selectedDayOfMonth}/${selectedMonth + 1}/${selectedYear}"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            var theDate = Date()
            theDate = sdf.parse(selectedDate)

            //for minute 1000*60
            val selectedDateInMinutes = theDate.time / (1000 * 60)

            //for days 1000*60*60*24
            val selectedDateInDays = theDate.time / (1000 * 60 * 60 * 24)

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val selectedCurrentDateInMinutes = currentDate.time / (1000 * 60)
            val differenceInMinutes = selectedCurrentDateInMinutes - selectedDateInMinutes
            tvSelectedDateInMinutes.text = differenceInMinutes.toString()

            val selectedCurrentDateInDays = currentDate.time / (1000 * 60 * 60 * 24)
            val differenceInDays = selectedCurrentDateInDays - selectedDateInDays
            tvSelectedDateInDays.text = differenceInDays.toString()

        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}