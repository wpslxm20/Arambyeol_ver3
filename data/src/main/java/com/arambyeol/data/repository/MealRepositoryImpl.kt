package com.arambyeol.data.repository

import android.os.Build
import com.arambyeol.data.dto.toEntity
import com.arambyeol.data.remote.MealApi
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.repository.MealRepository
import java.io.IOException
import javax.inject.Inject
import com.arambyeol.domain.common.Result
import com.arambyeol.domain.common.DomainError
import retrofit2.HttpException
import java.net.SocketTimeoutException

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
): MealRepository {
    override suspend fun getMealsByDate(date: String): Result<Meal> {
        return try {
            val response = mealApi.getMealsByDate(date)
            Result.Success(response.toEntity())
        } catch (e: IOException) {
            Result.Failure(DomainError.Network)
        } catch (e: HttpException) {
            val error = when (e.code()) {
                400, 404 -> DomainError.NotFound
                500 -> DomainError.Server
                else -> DomainError.Unknown
            }
            Result.Failure(error)
        } catch (e: SocketTimeoutException) {
            Result.Failure(DomainError.Timeout)
        } catch (e: Exception) {
            Result.Failure(DomainError.Unknown)
        }
    }

    override suspend fun getWeeklyMealsByDate(date: String): Result<List<Meal>> {
        return try {
            val response = mealApi.getWeeklyMealsByDate(date)
            Result.Success(response.toEntity())
        } catch (e: IOException) {
            Result.Failure(DomainError.Network)
        } catch (e: HttpException) {
            val error = when (e.code()) {
                400, 404 -> DomainError.NotFound
                500 -> DomainError.Server
                else -> DomainError.Unknown
            }
            Result.Failure(error)
        } catch (e: SocketTimeoutException) {
            Result.Failure(DomainError.Timeout)
        } catch (e: Exception) {
            Result.Failure(DomainError.Unknown)
        }
    }

}