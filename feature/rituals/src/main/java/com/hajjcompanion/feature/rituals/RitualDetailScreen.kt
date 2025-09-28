package com.hajjcompanion.feature.rituals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RitualDetailScreen(
    ritualId: String,
    modifier: Modifier = Modifier,
    vm: RitualDetailViewModel = hiltViewModel()
) {
    val state by vm.state(ritualId)
    Scaffold(topBar = { TopAppBar(title = { Text(text = state.title) }) }) { padding ->
        Content(padding, state, onToggle = { id, checked -> vm.setStepCompleted(id, checked) })
    }
}

@Composable
private fun Content(
    padding: PaddingValues,
    state: RitualDetailViewModel.UiState,
    onToggle: (Int, Boolean) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(padding)) {
        Text(
            text = "${'$'}{state.completedCount}/${'$'}{state.total}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(state.steps) { step ->
                ListItem(
                    headlineContent = { Text(step.title) },
                    supportingContent = { Text(step.description) },
                    trailingContent = {
                        Checkbox(checked = step.completed, onCheckedChange = { onToggle(step.id, it) })
                    }
                )
                Divider()
            }
        }
    }
}
