package com.example.eduscheduleapp.presentation.grades

import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.data.remote.dto.Student

data class GradesState(
    val isLoading: Boolean = false,
    val student: Student? = null,
    var totalList: MutableList<Mark>? = null,
    var belLangList: MutableList<Mark>? = null,
    var belLitList: MutableList<Mark>? = null,
    var rusLangList: MutableList<Mark>? = null,
    var rusLitList: MutableList<Mark>? = null,
    var engLangList: MutableList<Mark>? = null,
    var mathList: MutableList<Mark>? = null,
    var infList: MutableList<Mark>? = null,
    var historyList: MutableList<Mark>? = null,
    var societyList: MutableList<Mark>? = null,
    var geographyList: MutableList<Mark>? = null,
    var biologyList: MutableList<Mark>? = null,
    var physicsList: MutableList<Mark>? = null,
    var astronomyList: MutableList<Mark>? = null,
    var chemistryList: MutableList<Mark>? = null,
    val error: String = ""
)

/*Белорусский язык
Белорусская литература
Русский язык
Русская литература
Иностранный язык
Математика
Информатика
История
Обществоведение
География
Биология
Физика
Астрономия
Химия*/
