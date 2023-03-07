package com.cmaye.kmoviesstore.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class DateTimeText {
    companion object{
        const val TIME_FORMAT = "mm"
        const val TIME_FORMAT1 = "HH:mm"


        const val DATE_FORMAT1 = "dd-MMMM-yyyy"   // 05-March-2023
        const val DATE_FORMAT2 = "MMM dd,yyyy"   // Mar 03,2023
        const val DATE_FORMAT3 = "yyyy-MM-dd"


        @RequiresApi(Build.VERSION_CODES.O)
        fun convertDateFormat(currentFormat : String, changeFormat : String,convertDate : String) : String
        {

            var firstDateFormat = SimpleDateFormat(currentFormat)
            var secondDateFormat = SimpleDateFormat(changeFormat)
            val date = firstDateFormat.parse(convertDate)
            return secondDateFormat.format(date)
        }



        fun convertMinToHourMinute(currentFormat : String,changeFormat: String,convertMinute : String) : String
        {
            var firstTimeFormat = SimpleDateFormat(currentFormat)
            var secondTimeFormat = SimpleDateFormat(changeFormat)
            var date : Date = firstTimeFormat.parse(convertMinute)
            return secondTimeFormat.format(date).toString()
        }

        fun getHourAndMinutesFromMinute(currentMinute : Int) : String
        {
            val hours: Int = currentMinute / 60 //since both are ints, you get an int
            val minutes: Int = currentMinute % 60
            var hourFormat = String.format("%2d",hours)
            var minuteFormat = String.format("%2d",minutes)
            return "${hourFormat}h ${minuteFormat}min  "
        }
    }
}