package com.example.kkirikkiri.module.dto.board.response

import com.example.kkirikkiri.module.dto.board.CommentResponse

data class FindCommentsResponse(var page: Int, var pageCount: Int, var result : List<CommentResponse>)
