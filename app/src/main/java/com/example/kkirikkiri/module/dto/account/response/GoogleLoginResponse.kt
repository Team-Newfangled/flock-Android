package com.example.kkirikkiri.module.dto.account.response

import com.google.gson.annotations.SerializedName

data class GoogleLoginResponse(@SerializedName("user_id") var id : Int, var access_token : String, var refresh_token : String)

// grant_type": "Bearer ",
//		"access_token": "eryasdsdnwqnnw@sjdnjndnfjfqeifwjd",
//		"refresh_token": "eryasdsdnwqnnw@sajdnjfeifnfncncnjieqwu933njddn",
//		"access_token_expires_in": 720000