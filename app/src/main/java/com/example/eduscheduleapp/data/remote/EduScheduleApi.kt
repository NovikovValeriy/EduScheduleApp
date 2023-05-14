package com.example.eduscheduleapp.data.remote

import com.example.eduscheduleapp.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface EduScheduleApi {
    @POST("login")
    suspend fun authorization(@Body authRequest: AuthRequest): LoginData

    @GET("api/v1/events/")
    suspend fun getEvents(@Header("Authorization") access : String) : List<Event>

    @GET("api/v1/schedule/{id}")
    suspend fun getSchedule(@Header("Authorization") access : String, @Path("id") groupId : String): List<ScheduleSubject>

    @GET("api/v1/student/{id}")
    suspend fun getStudent(@Header("Authorization") access : String, @Path("id") studentId : String): Student

    @GET("api/v1/students")
    suspend fun getStudents(@Header("Authorization") access : String): List<Student>
}