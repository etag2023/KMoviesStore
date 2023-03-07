package com.cmaye.kmoviesstore.common

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Math.floor
import java.net.URL


class CommonFunction {
    companion object{
        fun getImage(imageLink : String) : Bitmap
        {
            val url = URL(GeneralClass.IMAGE_URL+ imageLink)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            return bmp
        }

        //hide keyboard
        fun Context.hideKeyboard(view: View) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        //hide keyboard
        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }


        fun roundDownToMultipleOf(baseValue: Double): Double
        {
            val number3digits:Double = Math.round(baseValue * 1000.0) / 1000.0
            val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
            return  Math.round(number2digits * 10.0) / 10.0
        }


        fun stringConverter(str : String,commaPosition : Int) : String
        {
            var stringArray = str.split("")
            var outputString = ""
            for (i in 0..stringArray.size)
            {
                if (i == commaPosition)
                {
                    outputString = outputString + ','
                }
                outputString = outputString + stringArray[i]
            }
            return outputString
        }












//
//        var popularText = "20891.2898"
//
//        var result = popularText.substringBeforeLast(".")
//        var test = result.takeLast(3)
////        var test2 = result.subSequence(result.length.)
////        test
//
//        var matest = result.substring(0,3) + "," + result.substring(3)
//        matest
//
//
//        var str = CommonFunction.stringConverter("28747493",3)
//        str
    }
}