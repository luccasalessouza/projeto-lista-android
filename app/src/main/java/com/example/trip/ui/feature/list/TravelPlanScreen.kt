package com.example.trip.ui.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trip.ui.UiEvent
import com.example.trip.ui.components.TravelActivityItem

/**
 * A tela principal (Composable) que exibe a lista de atividades de viagem.
 *
 * @param onNavigate Callback para lidar com eventos de navegação. Recebe a rota de destino.
 * @param viewModel A instância do [TravelPlanViewModel] fornecida pelo Hilt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelPlanScreen(
    onNavigate: (String) -> Unit,
    viewModel: TravelPlanViewModel = hiltViewModel()
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event.route)
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TravelPlanEvent.OnAddClick)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Atividade")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(todos.value) { todo ->
                TravelActivityItem(
                    todo = todo,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(TravelPlanEvent.OnItemClick(todo))
                    }
                )
            }
        }
    }
}