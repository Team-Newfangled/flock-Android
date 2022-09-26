package com.example.kkirikkiri.module.dto.account.response

import com.google.gson.annotations.SerializedName

data class ResultResponse (var result: List<Result>) {
    data class Result(@SerializedName("team-id") var teamId: Int, @SerializedName("team-name") var teamName : String)
}