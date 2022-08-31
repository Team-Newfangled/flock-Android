package com.example.kkirikkiri.module.dto.account



data class MessageResponse(var links : Links, var message : String){
    data class Links(var href : String, var method : String, var rel : String)
}
