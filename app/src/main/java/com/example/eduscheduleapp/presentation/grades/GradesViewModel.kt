package com.example.eduscheduleapp.presentation.grades

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.common.Resource
import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.data.remote.dto.ScheduleSubject
import com.example.eduscheduleapp.domain.use_case.get_student.GetStudentUseCase
import com.example.eduscheduleapp.presentation.schedule.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GradesViewModel @Inject constructor(
    private val getStudentUseCase: GetStudentUseCase
) : ViewModel() {

    private val _state = mutableStateOf(GradesState())
    val state: State<GradesState> = _state

    init {
        getStudent(DATA.person.student_id)
    }

    private fun getStudent(studenId : String){
        getStudentUseCase(studenId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    var totalList = mutableListOf<Mark>()
                    var belLangList = mutableListOf<Mark>()
                    var belLitList = mutableListOf<Mark>()
                    var rusLangList = mutableListOf<Mark>()
                    var rusLitList = mutableListOf<Mark>()
                    var engLangList = mutableListOf<Mark>()
                    var mathList = mutableListOf<Mark>()
                    var infList = mutableListOf<Mark>()
                    var historyList = mutableListOf<Mark>()
                    var societyList = mutableListOf<Mark>()
                    var geographyList = mutableListOf<Mark>()
                    var biologyList = mutableListOf<Mark>()
                    var physicsList = mutableListOf<Mark>()
                    var astronomyList = mutableListOf<Mark>()
                    var chemistryList = mutableListOf<Mark>()
                    result.data?.marks?.forEach {
                        when(it.subject){
                            "Белорусский язык" -> belLangList.add(it)
                            "Белорусская литература" -> belLitList.add(it)
                            "Русский язык" -> rusLangList.add(it)
                            "Русская литература" -> rusLitList.add(it)
                            "Иностранный язык" -> engLangList.add(it)
                            "Математика" -> mathList.add(it)
                            "Информатика" -> infList.add(it)
                            "История" -> historyList.add(it)
                            "Обществоведение" -> societyList.add(it)
                            "География" -> geographyList.add(it)
                            "Биология" -> biologyList.add(it)
                            "Физика" -> physicsList.add(it)
                            "Астрономия" -> astronomyList.add(it)
                            "Химия" -> chemistryList.add(it)
                        }
                        totalList.add(it)
                    }
                    _state.value = GradesState(
                        student = result.data!!,
                        totalList = totalList,
                        belLangList = belLangList,
                        belLitList = belLitList,
                        rusLangList = rusLangList,
                        rusLitList = rusLitList,
                        engLangList = engLangList,
                        mathList = mathList,
                        infList = infList,
                        historyList = historyList,
                        societyList = societyList,
                        geographyList = geographyList,
                        biologyList = biologyList,
                        physicsList = physicsList,
                        astronomyList = astronomyList,
                        chemistryList = chemistryList
                    )
                }
                is Resource.Error -> {
                    _state.value = GradesState(
                        error = result.message ?: "Непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = GradesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}