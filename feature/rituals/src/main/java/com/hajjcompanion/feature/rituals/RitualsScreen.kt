package com.hajjcompanion.feature.rituals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import com.hajjcompanion.feature.rituals.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RitualsScreen(
    modifier: Modifier = Modifier,
    viewModel: RitualsViewModel = hiltViewModel(),
    onRitualClick: (String) -> Unit = {}
) {
    val state by viewModel.state

    Scaffold(topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.title_rituals)) }) }) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding)) {
                CircularProgressIndicator(Modifier.padding(24.dp))
            }
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize().padding(padding)
            ) {
                item {
                    OutlinedTextField(
                        value = state.query,
                        onValueChange = viewModel::setQuery,
                        label = { Text(text = stringResource(id = R.string.label_search)) },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    )
                }
                items(state.rituals) { ritual ->
                    ListItem(
                        headlineContent = { Text(ritual.name) },
                        supportingContent = { Text(ritual.fullDescription.take(100)) },
                        trailingContent = { Text(ritual.category.name) },
                        modifier = Modifier.clickable { onRitualClick(ritual.id) }
                    )
                    Divider()
                }
            }
        }
    }
}
