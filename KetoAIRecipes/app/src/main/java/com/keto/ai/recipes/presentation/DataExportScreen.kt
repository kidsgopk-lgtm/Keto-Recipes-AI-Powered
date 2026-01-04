package com.keto.ai.recipes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keto.ai.recipes.domain.DataExportViewModel

@Composable
fun DataExportScreen() {
    val viewModel: DataExportViewModel = viewModel()
    val status by viewModel.exportStatus
    Column(Modifier.padding(16.dp)) {
        Text("Data Export & Backup", style = MaterialTheme.typography.h5)
        Button(onClick = { viewModel.exportData() }) {
            Text("Export Data")
        }
        Text("Status: $status")
    }
}
