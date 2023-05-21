package com.example.eduscheduleapp.presentation.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.ScheduleSubject
import com.example.eduscheduleapp.domain.use_case.get_schedule.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    init {
        getSchedule(DATA.person.group_id)
    }

    private fun getSchedule(groupId : String){
        getScheduleUseCase(groupId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    var mondayList = mutableListOf<ScheduleSubject>()
                    var tuesdayList = mutableListOf<ScheduleSubject>()
                    var wednesdayList = mutableListOf<ScheduleSubject>()
                    var thursdayList = mutableListOf<ScheduleSubject>()
                    var fridayList = mutableListOf<ScheduleSubject>()
                    result.data?.forEach{
                        when(it.day_of_week){
                            "Понедельник" -> {
                                mondayList.add(it)
                            }
                            "Вторник" -> {
                                tuesdayList.add(it)
                            }
                            "Среда" -> {
                                wednesdayList.add(it)
                            }
                            "Четверг" -> {
                                thursdayList.add(it)
                            }
                            "Пятница" -> {
                                fridayList.add(it)
                            }
                        }
                    }
                    _state.value = ScheduleState(schedule = result.data!!, mondayList = mondayList, tuesdayList = tuesdayList, wednesdayList = wednesdayList, thursdayList = thursdayList, fridayList = fridayList)
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(
                        error = result.message ?: "Непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}