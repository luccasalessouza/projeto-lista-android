package com.example.trip.ui.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trip.data.TravelActivityRepository
import com.example.trip.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para a tela de lista de atividades ([TravelPlanScreen]).
 * Gerencia o estado da UI e lida com os eventos do usuário para esta tela.
 *
 * @property repository O repositório para acesso aos dados das atividades.
 */
@HiltViewModel
class TravelPlanViewModel @Inject constructor(
    private val repository: TravelActivityRepository
) : ViewModel() {

    /**
     * Um Flow que expõe a lista de atividades do repositório.
     * O [stateIn] converte o Flow frio em um Flow quente que pode ser observado pela UI,
     * mantendo o último valor emitido.
     */
    val todos = repository.getTravelActivities()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _uiEvent = Channel<UiEvent>()
    /**
     * Expõe eventos únicos para a UI, como navegação ou exibição de Snackbars.
     * Usado para ações que não devem ser repetidas em uma recriação da UI (ex: girar a tela).
     */
    val uiEvent = _uiEvent.receiveAsFlow()

    /**
     * Processa os eventos enviados pela UI ([TravelPlanScreen]).
     * @param event O evento a ser processado, vindo de [TravelPlanEvent].
     */
    fun onEvent(event: TravelPlanEvent) {
        when (event) {
            is TravelPlanEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    repository.deleteTravelActivity(event.todo)
                }
            }
            is TravelPlanEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate("add_edit_screen/${event.todo.id}"))
            }
            is TravelPlanEvent.OnAddClick -> {
                sendUiEvent(UiEvent.Navigate("add_edit_screen/-1"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}