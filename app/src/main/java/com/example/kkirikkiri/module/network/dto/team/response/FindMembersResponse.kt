package com.example.kkirikkiri.module.network.dto.team.response

import com.google.gson.annotations.SerializedName

data class FindMembersResponse(var page : Int, @SerializedName("page-count") var pageCount : String, var results : List<Results>) {
    data class Results(var approved : Boolean, var id : Int, var name : String, var role : String,@SerializedName("self-url") var selfUrl: String)
}
