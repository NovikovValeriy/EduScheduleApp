package com.example.eduscheduleapp.domain.use_case.get_schedule

import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Event
import com.example.eduscheduleapp.data.remote.dto.ScheduleSubject
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val repository: EduScheduleRepository
){

    operator fun invoke(accessToken : String, groupId : String): Flow<Resource<List<ScheduleSubject>>> = flow{
        try {
            emit(Resource.Loading<List<ScheduleSubject>>())
            val schedule = repository.getSchedule(accessToken, groupId)
            emit(Resource.Success<List<ScheduleSubject>>(schedule))
        } catch (e: HttpException){
            emit(Resource.Error<List<ScheduleSubject>>("1"))
        } catch (e: IOException){
            emit(Resource.Error<List<ScheduleSubject>>("2"))
        }
    }
}