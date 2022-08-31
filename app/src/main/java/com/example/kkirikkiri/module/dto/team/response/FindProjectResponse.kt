package com.example.kkirikkiri.module.dto.team.response


data class FindProjectResponse (var page : Int, var pageCount : Int, var results : List<Results>) {
    data class Results(var id : Int, var name : String)
}