package com.raian.imagesearchapp.ui.theme.network

import com.raian.imagesearchapp.ui.theme.network.models.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/")
    suspend fun getQueryImage(
        @Query("q") query:String,
        @Query("key") key: String,
        @Query("image_type") imageType:String
    ):PixabayResponse

}