package com.example.eduscheduleapp.domain.use_case.get_student

import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Student
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStudentUseCase @Inject constructor(
    private val repository: EduScheduleRepository
) {

    operator fun invoke(accessToken: String, studentId: String): Flow<Resource<Student>> = flow{
        try {
            emit(Resource.Loading<Student>())
            val student = repository.getStudent(accessToken, studentId)
            emit(Resource.Success<Student>(student))
        } catch (e: HttpException){
            emit(Resource.Error<Student>("1"))
        } catch (e: IOException){
            emit(Resource.Error<Student>("2"))
        }
    }
}