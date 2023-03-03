package com.cmaye.kmoviesstore.model

import com.google.gson.annotations.SerializedName

data class Genere(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String
)
