package com.example.eduscheduleapp.domain.use_case.get_students

import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Student
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStudentsUseCase @Inject constructor(
    private val repository: EduScheduleRepository
) {

    operator fun invoke(accessToken: String): Flow<Resource<List<Student>>> = flow{
        try {
            emit(Resource.Loading<List<Student>>())
            val students = repository.getStudents(accessToken)
            emit(Resource.Success<List<Student>>(students))
        } catch (e: HttpException){
            emit(Resource.Error<List<Student>>(e.localizedMessage ?: "Непредвиденная ошибка сервера."))
        } catch (e: IOException){
            emit(Resource.Error<List<Student>>("Ошибка подключения. Проверьте соединение с сетью."))
        }
    }
}