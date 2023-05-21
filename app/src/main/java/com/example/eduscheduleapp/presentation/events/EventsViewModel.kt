package com.example.eduscheduleapp.presentation.events

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.domain.use_case.get_events.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(EventsState())
    val state: State<EventsState> = _state


    init {
        getEvents()
    }



    private fun getEvents(){
        getEventsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    result.data?.forEach{
                        val inputDate = it.date
                        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
                        val outputFormat = SimpleDateFormat("dd.MM.yyyy")
                        it.date = outputFormat.format(inputFormat.parse(inputDate))
                    }
                    _state.value = EventsState(events = result.data!!)
                }
                is Resource.Error -> {
                    _state.value = EventsState(
                        error = result.message ?: "Непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = EventsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}