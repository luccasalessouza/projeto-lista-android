package com.example.trip.domain

/**
 * Representa os níveis de prioridade que uma atividade pode ter.
 * A ordem (HIGH, MEDIUM, LOW) é usada para a ordenação na base de dados.
 */
enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}