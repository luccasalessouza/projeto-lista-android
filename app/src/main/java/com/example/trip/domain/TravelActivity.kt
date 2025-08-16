package com.example.trip.domain

/**
 * Representa o modelo de dados de uma atividade de viagem na camada de domínio.
 * Esta é a representação "limpa" do objeto, usada pela UI e pela lógica de negócio.
 *
 * @property id O identificador único da atividade. O valor padrão é 0 para novas atividades.
 * @property title O título da atividade (obrigatório).
 * @property description A descrição opcional da atividade.
 * @property lastModified O timestamp (em milissegundos) da última modificação.
 * @property isCompleted Indica se a atividade foi concluída.
 * @property priority O nível de prioridade da atividade.
 */
data class TravelActivity(
    val id: Int = 0,
    val title: String,
    val description: String?,
    val lastModified: Long,
    val isCompleted: Boolean,
    val priority: Priority
)