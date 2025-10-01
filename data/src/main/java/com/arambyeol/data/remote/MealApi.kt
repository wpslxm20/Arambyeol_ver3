package com.arambyeol.data.remote

import com.arambyeol.data.dto.MealDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MealApi {
    @GET("/plans/{date}")
    suspend fun getMealsByDate(
        @Path("date") date: String
    ): MealDto
}