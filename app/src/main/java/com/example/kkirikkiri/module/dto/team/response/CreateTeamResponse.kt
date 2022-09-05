package com.example.kkirikkiri.module.dto.team.response

import com.google.gson.annotations.SerializedName

data class CreateTeamResponse (@SerializedName("team-id") var teamId : Int,@SerializedName("team-name") var teamName : String)