package com.cmaye.kmoviesstore.common

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class SharePref {
    companion object{

        fun saveSharePref(context : Context,saveKey : String,saveValue : String)
        {

            var sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(saveKey, saveValue)
            editor.commit()
        }

        fun getSharePref(context: Context,saveKey: String) : String
        {
            var sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPreferences.getString(saveKey,"")!!
        }

    }
}