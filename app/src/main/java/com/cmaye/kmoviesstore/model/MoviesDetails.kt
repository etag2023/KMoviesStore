package com.cmaye.kmoviesstore.model

import com.google.gson.annotations.SerializedName


data class MovieDetail(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("genres")
    val genres: List<Genere>? = listOf(),
    @SerializedName("credits")
    val credits: Credits? = null,
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("tagline")
    val tagline: String? = ""

)
data class Actor(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profile_path: String?
)
data class Credits(
    @SerializedName("cast") var cast: List<Actor>? = listOf()
)


//
//class MoviesDetails(
//    var adult : Boolean,
//    var backdrop_path : String? ,
//    var belongs_to_collection : Any?,
//    var budget : Int,
//    var genres : Genres,
//    var homepage : String?,
//    var id : Int,
//    var imdb_id : String?,
//    var original_language : String,
//    var original_title : String,
//    var overview : String?,
//    var popularity : Int,
//    var poster_path : String?,
//    var production_companies : MutableList<ProductionCompanies>,
//    var production_countries : MutableList<ProductionCountries>,
//    var release_date : String,
//    var revenue : Int,
//    var runtime : Int?,
//    var spoken_languages: MutableList<SpokenLanguages>?,
//    var status : String,
//    var tagline : String?,
//    var title : String,
//    var video : Boolean,
//    var vote_average : Int?,
//    var vote_count : Int?,
//    )