package com.example.kkirikkiri.module.dto.account.response

data class ResultResponse (var results: Result) {
    data class Result(var name: String)
}