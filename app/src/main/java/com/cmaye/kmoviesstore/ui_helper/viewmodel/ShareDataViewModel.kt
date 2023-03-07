package com.cmaye.kmoviesstore.ui_helper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareDataViewModel : ViewModel() {

//    private val _boolFavorite = MutableLiveData<Boolean>(false)
//    var boolFavorite: LiveData<Boolean> = _boolFavorite

    var tempStr = MutableLiveData<String>()
    fun insertStr(result : String){
        tempStr.value = result
    }

//
//    val resultsItem = MutableLiveData<ResultsItem>()
//
//    fun boolFavoriteFun(results: ResultsItem) {
//        resultsItem.value = results
//    }

}