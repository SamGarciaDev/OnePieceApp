package edu.samgarcia.onepieceapp.data.remote

import edu.samgarcia.onepieceapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OnePieceApi {
    @GET("/onepiece/characters")
    suspend fun getAllCharacters(@Query("page") page: Int = 1): ApiResponse

    @GET("/onepiece/characters/search")
    suspend fun searchCharacters(@Query("name") name: String): ApiResponse
}