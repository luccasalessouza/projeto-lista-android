package com.example.trip.data

import com.example.trip.domain.TravelActivity
import kotlinx.coroutines.flow.Flow

/**
 * Interface que define o contrato para o repositório de atividades de viagem.
 * Abstrai a origem dos dados, permitindo que a ViewModel interaja com os dados
 * sem conhecer os detalhes de implementação (banco de dados, API, etc.).
 */
interface TravelActivityRepository {

    /**
     * Insere ou atualiza uma atividade de viagem.
     * @param travelActivity O objeto [TravelActivity] a ser salvo.
     */
    suspend fun insertTravelActivity(travelActivity: TravelActivity)

    /**
     * Deleta uma atividade de viagem.
     * @param travelActivity O objeto [TravelActivity] a ser deletado.
     */
    suspend fun deleteTravelActivity(travelActivity: TravelActivity)

    /**
     * Busca uma atividade de viagem específica pelo seu ID.
     * @param id O ID da atividade.
     * @return O objeto [TravelActivity] correspondente ou nulo.
     */
    suspend fun getTravelActivityById(id: Int): TravelActivity?

    /**
     * Retorna um Flow com a lista de todas as atividades de viagem, ordenadas.
     * @return Um [Flow] que emite a lista de [TravelActivity].
     */
    fun getTravelActivities(): Flow<List<TravelActivity>>
}