package com.example.kkirikkiri.module.network.dto.team.response

import com.google.gson.annotations.SerializedName


data class FindProjectResponse (var page : Int, @SerializedName("page-count") var pageCount : Int, var results : List<Results>) {
    data class Results(@SerializedName("cover_image") var coverImage : String, var id : Int, var name : String)
}