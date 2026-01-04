package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.keto.ai.recipes.model.SymptomLog

class SymptomLogViewModel : ViewModel() {
    val logs = MutableLiveData<List<SymptomLog>>(emptyList())
    fun addLog(log: SymptomLog) {
        logs.value = logs.value?.plus(log)
    }
}
