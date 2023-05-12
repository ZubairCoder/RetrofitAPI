package com.example.retrofitapi


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getData(): Call<responsePost>
    @POST("posts")
    fun  postData(@Body responsePostItem: responsePostItem) : Call<responsePostItem>
    @GET("posts/{id}")
    fun getOneData(@Path("id") id : Int) : Call<responsePostItem>

}