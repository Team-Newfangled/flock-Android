package com.example.kkirikkiri.module.dto.board.response

import com.google.gson.annotations.SerializedName

data class FindBoardPageResponse(var page : Int, @SerializedName("page-count") var pageCount: Int, var results : List<Results>) {
    data class Results(var content : String, var files: List<File>, var id : Int,@SerializedName("writer-id") var writerId : Int) {
        data class File(var file : String, var id : Int)
    }
}
