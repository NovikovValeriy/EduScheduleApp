package com.example.eduscheduleapp.presentation.schedule

import com.example.eduscheduleapp.data.remote.dto.ScheduleSubject

data class ScheduleState(
    val isLoading: Boolean = false,
    var schedule: List<ScheduleSubject> = emptyList(),
    var mondayList: MutableList<ScheduleSubject>? = null,
    var tuesdayList: MutableList<ScheduleSubject>? = null,
    var wednesdayList: MutableList<ScheduleSubject>? = null,
    var thursdayList: MutableList<ScheduleSubject>? = null,
    var fridayList: MutableList<ScheduleSubject>? = null,
    val error: String = ""
)
