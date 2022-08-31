package com.example.kkirikkiri.module.dto.board.response

data class FindBoardResponse(var content: String, var files : List<File>, var id : Int, var writerId : Int) {
    data class File(var file:String, var id : Int)
}
