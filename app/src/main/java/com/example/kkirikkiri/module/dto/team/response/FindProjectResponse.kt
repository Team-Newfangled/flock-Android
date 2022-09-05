package com.example.kkirikkiri.module.dto.team.response

import com.google.gson.annotations.SerializedName


data class FindProjectResponse (var page : Int, @SerializedName("page-count") var pageCount : Int, var results : List<Results>) {
    data class Results(var id : Int, var name : String)
}