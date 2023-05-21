package com.example.eduscheduleapp.domain.repository

import com.example.eduscheduleapp.data.remote.dto.*
import retrofit2.http.Header

interface EduScheduleRepository {

    suspend fun authorization(authRequest: AuthRequest): LoginData

    suspend fun getEvents(accessToken : String): List<Event>

    suspend fun getSchedule(accessToken: String, groupId : String): List<ScheduleSubject>

    suspend fun getStudent(accessToken: String, studentId: String): Student

    suspend fun getStudents(accessToken : String): List<Student>

    suspend fun refreshToken()
}