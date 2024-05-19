package com.example.myapplication2


class UserResponse : ArrayList<UserResponseItem>()
data class UserResponseItem(
 val Email: String,
 val Gender: String,
 val Mobile: String,
 val Name: String,
 val _id: String
)
// class CommonResponse : ArrayList<GetResponse>()
//data class GetResponse(var _id :String, var Name :String, var Email :String, var Mobile :String,var Gender:String)
