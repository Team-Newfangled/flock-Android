package com.example.kkirikkiri.module.dto.board.response

data class FindBoardPageResponse(var page : Int, var pageCount: Int, var result : List<Results>) {
    data class Results(var content : String, var files: List<File>, var id : Int, var writerId : Int) {
        data class File(var file : String, var id : Int)
    }
}
