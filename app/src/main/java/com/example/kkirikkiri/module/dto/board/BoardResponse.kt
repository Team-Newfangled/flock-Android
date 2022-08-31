package com.example.kkirikkiri.module.dto.board

data class BoardResponse(var content: String, var files : List<File>, var id : Int, var writerId : Int) {
    data class File(var file:String, var id : Int)
}
