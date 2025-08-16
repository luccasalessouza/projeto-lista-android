package com.example.trip.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trip.data.TravelActivityRepository
import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import com.example.trip.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para a tela de adicionar/editar atividade ([AddEditTravelActivityScreen]).
 * Gerencia o estado da UI (título, descrição, prioridade) e lida com a lógica de salvar os dados.
 *
 * @property repository O repositório para acesso aos dados das atividades.
 * @constructor
 * @param savedStateHandle Usado para acessar os argumentos de navegação, como o ID da atividade.
 */
@HiltViewModel
class AddEditTravelActivityViewModel @Inject constructor(
    private val repository: TravelActivityRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var activityId: Int = savedStateHandle.get<Int>("id") ?: -1

    /** O estado atual do campo de título, observável pela UI. */
    var title by mutableStateOf("")
        private set

    /** O estado atual do campo de descrição, observável pela UI. */
    var description by mutableStateOf("")
        private set

    /** O estado atual da seleção de prioridade, observável pela UI. */
    var priority by mutableStateOf(Priority.MEDIUM)
        private set

    private val _uiEvent = Channel<UiEvent>()
    /**
     * Expõe eventos únicos para a UI, como navegação para a tela anterior.
     */
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        // Se um ID válido foi passado, carrega os dados da atividade para edição.
        if (activityId != -1) {
            viewModelScope.launch {
                repository.getTravelActivityById(activityId)?.let { activity ->
                    title = activity.title
                    description = activity.description ?: ""
                    priority = activity.priority
                }
            }
        }
    }

    /**
     * Processa os eventos enviados pela [AddEditTravelActivityScreen].
     * @param event O evento a ser processado.
     */
    fun onEvent(event: AddEditTravelActivityEvent) {
        when (event) {
            is AddEditTravelActivityEvent.OnTitleChange -> title = event.title
            is AddEditTravelActivityEvent.OnDescriptionChange -> description = event.description
            is AddEditTravelActivityEvent.OnPriorityChange -> priority = event.priority
            is AddEditTravelActivityEvent.OnSaveClick -> onSaveClick()
        }
    }

    private fun onSaveClick() {
        viewModelScope.launch {
            if (title.isBlank()) {
                sendUiEvent(UiEvent.ShowSnackbar("O título não pode ser vazio"))
                return@launch
            }
            val activityToSave = TravelActivity(
                id = if (activityId == -1) 0 else activityId,
                title = title,
                description = description.takeIf { it.isNotBlank() },
                lastModified = System.currentTimeMillis(),
                isCompleted = false,
                priority = priority
            )
            repository.insertTravelActivity(activityToSave)
            sendUiEvent(UiEvent.NavigateBack)
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}