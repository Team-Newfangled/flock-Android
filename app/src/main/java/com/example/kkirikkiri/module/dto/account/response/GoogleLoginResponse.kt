package com.example.kkirikkiri.module.dto.account.response

data class GoogleLoginResponse(var grant_type : String, var access_token : String, var refresh_token : String, var access_token_expires_in : Long)

// grant_type": "Bearer ",
//		"access_token": "eryasdsdnwqnnw@sjdnjndnfjfqeifwjd",
//		"refresh_token": "eryasdsdnwqnnw@sajdnjfeifnfncncnjieqwu933njddn",
//		"access_token_expires_in": 720000