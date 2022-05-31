package com.example.testrainbowsmile.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id_pos")
    val idPos: Long = 0,
    @SerializedName("id_record")
    val idRecord: Long = 0,
    @SerializedName("id_hd_route")
    val idHdRoute: Long = 0,
    @SerializedName("nom_route")
    val nomRoute: String = "null",
    @SerializedName("nom_zak")
    val nomZak: String = "null",
    @SerializedName("nom_nakl")
    val nomNakl: String = "null",
)
