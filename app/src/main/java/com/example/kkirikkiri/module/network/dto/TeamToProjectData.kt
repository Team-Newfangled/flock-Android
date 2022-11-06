package com.example.kkirikkiri.module.network.dto

import com.example.kkirikkiri.module.network.dto.team.response.FindProjectResponse

data class TeamToProjectData (var id : Int, var projects : List<FindProjectResponse.Results>)