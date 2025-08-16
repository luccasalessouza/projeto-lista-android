package com.example.trip.ui.feature.addedit

import com.example.trip.domain.Priority

/**
 * Define os eventos que podem ser acionados a partir da tela de Adicionar/Editar Atividade.
 * Estes eventos são enviados da UI para o [AddEditTravelActivityViewModel] para processamento.
 */
sealed class AddEditTravelActivityEvent {
    /**
     * Evento acionado quando o texto do campo de título é alterado.
     * @param title O novo texto do título.
     */
    data class OnTitleChange(val title: String) : AddEditTravelActivityEvent()

    /**
     * Evento acionado quando o texto do campo de descrição é alterado.
     * @param description O novo texto da descrição.
     */
    data class OnDescriptionChange(val description: String) : AddEditTravelActivityEvent()

    /**
     * Evento acionado quando a seleção de prioridade é alterada.
     * @param priority O novo nível de [Priority] selecionado.
     */
    data class OnPriorityChange(val priority: Priority) : AddEditTravelActivityEvent()

    /**
     * Evento acionado quando o usuário clica no botão para salvar a atividade.
     */
    object OnSaveClick : AddEditTravelActivityEvent()
}