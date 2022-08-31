package com.example.kkirikkiri.module.dto



data class MessageResponse(var links : List<Links>, var message : String){
    data class Links(var href : String, var method : String, var rel : String)
}
