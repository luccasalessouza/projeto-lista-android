package com.example.trip.data

import com.example.trip.domain.Priority
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Suite de testes unitários para a classe [Converters].
 * Estes testes garantem que a conversão de tipos de e para a base de dados
 * funciona como esperado.
 */
class ConvertersTest {

    private val converters = Converters()

    /**
     * Testa se a função `fromPriority` converte corretamente uma String
     * de volta para o enum [Priority] correspondente.
     */
    @Test
    fun `fromPriority deve converter String para o Enum Priority correto`() {
        assertEquals(Priority.HIGH, converters.fromPriority("HIGH"))
        assertEquals(Priority.MEDIUM, converters.fromPriority("MEDIUM"))
        assertEquals(Priority.LOW, converters.fromPriority("LOW"))
    }

    /**
     * Testa se a função `toPriority` converte corretamente um enum [Priority]
     * para a sua representação em String.
     */
    @Test
    fun `toPriority deve converter Enum Priority para a String correta`() {
        assertEquals("HIGH", converters.toPriority(Priority.HIGH))
        assertEquals("MEDIUM", converters.toPriority(Priority.MEDIUM))
        assertEquals("LOW", converters.toPriority(Priority.LOW))
    }
}