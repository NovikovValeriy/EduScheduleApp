package com.example.eduscheduleapp.presentation.rating

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.domain.use_case.get_students.GetStudentsUseCase
import com.example.eduscheduleapp.presentation.grades.roundOffDecimal
import com.example.eduscheduleapp.presentation.schedule.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RatingState())
    val state: State<RatingState> = _state

    init {
        getStudents(DATA.person.access)
    }

    private fun getStudents(accessToken: String){
        getStudentsUseCase(accessToken).onEach { result ->
            when(result){
                is Resource.Success -> {
                    result.data?.forEach {
                        it.GPA = roundOffDecimal(calculateGPA(it.marks))!!
                    }
                    _state.value = RatingState(students = result.data!!)
                }
                is Resource.Error -> {
                    _state.value = RatingState(
                        error = result.message ?: "Непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RatingState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun calculateGPA(list : List<Mark>) : Double{
        var GPA = 0.0
        var count = true
        list.forEach {
            if(count) GPA = it.value.toDouble()
            else GPA = (GPA + it.value) / 2
            count = false
        }
        return GPA
    }

    /*private fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }*/
}