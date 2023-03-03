package com.cmaye.kmoviesstore.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL


class CommonFunction {
    companion object{
        fun getImage(imageLink : String) : Bitmap
        {
            val url = URL(GeneralClass.IMAGE_URL+ imageLink)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            return bmp
        }
    }
}