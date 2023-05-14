package com.example.eduscheduleapp.presentation.events

import com.example.eduscheduleapp.data.remote.dto.Event

data class EventsState(
    val isLoading : Boolean = false,
    val events : List<Event> = emptyList(),
    val error : String = ""
)
