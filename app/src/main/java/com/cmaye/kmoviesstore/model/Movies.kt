package com.cmaye.kmoviesstore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movies(
    val MoviesID : Int,
    val MoviesTitle : String,
    val MovieRatting : String,
    val MoviesSummary : String,
) : Parcelable