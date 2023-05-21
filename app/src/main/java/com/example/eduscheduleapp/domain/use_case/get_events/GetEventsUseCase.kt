package com.example.eduscheduleapp.domain.use_case.get_events

import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Event
import com.example.eduscheduleapp.domain.repository.EduScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EduScheduleRepository
) {

    operator fun invoke(accessToken : String): Flow<Resource<List<Event>>> = flow {
        try {
            emit(Resource.Loading<List<Event>>())
            val events = repository.getEvents(accessToken)
            emit(Resource.Success<List<Event>>(events))
        } catch (e: HttpException){
            emit(Resource.Error<List<Event>>("1"))
        } catch (e: IOException){
            emit(Resource.Error<List<Event>>("2"))
        }
    }
}