package com.ivanzulyan.andchallenge5.network

import com.ivanzulyan.andchallenge5.model.ResponsePopularMovie
import com.ivanzulyan.andchallenge5.model.SerialResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/3/movie/popular?api_key=4503dd264f6c4ea4e0cddab313fca13d")
    fun getPopularMovie(): Call<ResponsePopularMovie>

    @GET("/3/tv/popular?api_key=4503dd264f6c4ea4e0cddab313fca13d")
    fun getTvSerial(): Call<SerialResponse>
}