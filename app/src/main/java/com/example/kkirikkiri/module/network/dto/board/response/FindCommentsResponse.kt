package com.example.kkirikkiri.module.network.dto.board.response

import com.example.kkirikkiri.module.network.dto.board.CommentResponse
import com.google.gson.annotations.SerializedName

data class FindCommentsResponse(var page: Int, @SerializedName("page-count") var pageCount: Int, var results : List<CommentResponse>)
