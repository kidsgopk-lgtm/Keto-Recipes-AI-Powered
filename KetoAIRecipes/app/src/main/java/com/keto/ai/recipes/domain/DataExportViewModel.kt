package com.keto.ai.recipes.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class DataExportViewModel : ViewModel() {
    val exportStatus = MutableLiveData<String>("")
    fun exportData() {
        // ... logic to export health and recipe data ...
        exportStatus.value = "Export complete"
    }
}
