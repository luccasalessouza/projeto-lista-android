package com.example.trip.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trip.domain.Priority

/**
 * Representa a tabela `TravelActivityEntity` no banco de dados Room.
 * Esta classe define a estrutura dos dados como eles são armazenados localmente.
 *
 * @property id A chave primária da tabela, gerada automaticamente.
 * @property title O título da atividade.
 * @property description A descrição opcional da atividade.
 * @property lastModified O timestamp da última modificação, para ordenação.
 * @property isCompleted O estado de conclusão da atividade.
 * @property priority O nível de prioridade, armazenado como String.
 */
@Entity
data class TravelActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val lastModified: Long,
    val isCompleted: Boolean,
    val priority: Priority
)