package com.example.trip.ui.feature.addedit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trip.domain.Priority
import com.example.trip.ui.UiEvent

/**
 * A tela (Composable) para adicionar uma nova atividade ou editar uma existente.
 *
 * @param onNavigateBack Callback invocado para acionar a navegação de volta para a tela anterior.
 * @param viewModel A instância do [AddEditTravelActivityViewModel] fornecida pelo Hilt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTravelActivityScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddEditTravelActivityViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateBack -> onNavigateBack()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message, actionLabel = event.action)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTravelActivityEvent.OnSaveClick)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Salvar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = { viewModel.onEvent(AddEditTravelActivityEvent.OnTitleChange(it)) },
                placeholder = { Text(text = "Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.description,
                onValueChange = { viewModel.onEvent(AddEditTravelActivityEvent.OnDescriptionChange(it)) },
                placeholder = { Text(text = "Descrição (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Prioridade", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Priority.values().forEach { priority ->
                    Row(
                        Modifier.selectable(
                            selected = (priority == viewModel.priority),
                            onClick = { viewModel.onEvent(AddEditTravelActivityEvent.OnPriorityChange(priority)) }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (priority == viewModel.priority),
                            onClick = { viewModel.onEvent(AddEditTravelActivityEvent.OnPriorityChange(priority)) }
                        )
                        Text(text = priority.name.lowercase().replaceFirstChar { it.titlecase() })
                    }
                }
            }
        }
    }
}