package com.example.kkirikkiri.module.dto.team.response

data class FindMembersResponse(var page : Int, var pageCount : String, var result : List<Results>) {
    data class Results(var approved : Boolean, var id : Int, var name : String, var role : String, var selfUrl: String)
}
