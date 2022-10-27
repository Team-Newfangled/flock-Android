package com.example.kkirikkiri.module.network.dto.board

import com.google.gson.annotations.SerializedName

data class BoardResponse(var content: String, var files : List<File>, var id : Int, @SerializedName("write-id") var writerId : Int) {
    data class File(var file:String, var id : Int)
}
