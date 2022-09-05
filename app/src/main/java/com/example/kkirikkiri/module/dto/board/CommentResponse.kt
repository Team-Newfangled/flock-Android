package com.example.kkirikkiri.module.dto.board

import com.google.gson.annotations.SerializedName


data class CommentResponse (@SerializedName("board-id") var boardId: Int, var comment : String, var id : Int,@SerializedName("write-id") var writeId: Int)