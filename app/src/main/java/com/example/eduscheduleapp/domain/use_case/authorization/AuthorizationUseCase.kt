package com.example.eduscheduleapp.domain.use_case.authorization

import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.AuthRequest
import com.example.eduscheduleapp.data.remote.dto.LoginData
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val repository : EduScheduleRepository
) {

    operator fun invoke(authRequest: AuthRequest): Flow<Resource<LoginData>> = flow {
        try{
            emit(Resource.Loading<LoginData>())
            val loginData = repository.authorization(authRequest)
            emit(Resource.Success<LoginData>(loginData))
        } catch (e: HttpException){
            emit(Resource.Error<LoginData>(/*e.localizedMessage ?: */"Неправильный логин или пароль"))
        } catch (e: IOException){
            emit(Resource.Error<LoginData>("Ошибка подключения. Проверьте соединение с сетью."))
        }
    }
}