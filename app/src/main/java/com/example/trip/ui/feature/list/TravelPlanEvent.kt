package com.example.trip.ui.feature.list

import com.example.trip.domain.TravelActivity

/**
 * Define os eventos que podem ser acionados a partir da tela da Lista de Atividades.
 * Estes eventos são enviados da UI para o [TravelPlanViewModel].
 */
sealed class TravelPlanEvent {
    /**
     * Evento para deletar uma atividade específica.
     * @param todo O objeto [TravelActivity] a ser deletado.
     */
    data class OnDeleteClick(val todo: TravelActivity) : TravelPlanEvent()

    /**
     * Evento acionado quando um item da lista é clicado, para fins de edição.
     * @param todo O objeto [TravelActivity] que foi clicado.
     */
    data class OnItemClick(val todo: TravelActivity) : TravelPlanEvent()

    /**
     * Evento acionado quando o botão de adicionar nova atividade é clicado.
     */
    object OnAddClick : TravelPlanEvent()
}