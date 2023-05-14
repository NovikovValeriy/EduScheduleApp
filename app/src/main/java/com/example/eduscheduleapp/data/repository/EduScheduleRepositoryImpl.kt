package com.example.eduscheduleapp.data.repository

import com.example.eduscheduleapp.data.remote.EduScheduleApi
import com.example.eduscheduleapp.data.remote.dto.*
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import javax.inject.Inject

class EduScheduleRepositoryImpl @Inject constructor(
    private val api : EduScheduleApi
) : EduScheduleRepository{

    override suspend fun authorization(authRequest: AuthRequest): LoginData {
        return api.authorization(authRequest)
    }

    override suspend fun getEvents(accessToken: String): List<Event> {
        return api.getEvents("Bearer $accessToken")
    }

    override suspend fun getSchedule(accessToken: String, groupId: String): List<ScheduleSubject> {
        return api.getSchedule("Bearer $accessToken", groupId)
    }

    override suspend fun getStudent(accessToken: String, studentId: String): Student {
        return api.getStudent("Bearer $accessToken", studentId)
    }

    override suspend fun getStudents(accessToken: String): List<Student> {
        return api.getStudents("Bearer $accessToken")
    }
}