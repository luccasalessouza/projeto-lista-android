package com.example.trip.data

import androidx.room.TypeConverter
import com.example.trip.domain.Priority

/**
 * Conversores de tipo para o Room.
 * O Room só consegue armazenar tipos de dados primitivos. Esta classe ensina o Room a
 * converter tipos complexos (como [Priority] e [Date]) em tipos que ele consegue
 * armazenar na base de dados (como String e Long), e vice-versa.
 */
class Converters {

    /**
     * Converte uma String da base de dados para um enum [Priority].
     * @param value A string que representa a prioridade (ex: "HIGH").
     * @return O enum [Priority] correspondente.
     */
    @TypeConverter
    fun fromPriority(value: String): Priority {
        return Priority.valueOf(value)
    }

    /**
     * Converte um enum [Priority] para uma String a ser armazenada na base de dados.
     * @param priority O enum [Priority] a ser convertido.
     * @return A representação em String do enum.
     */
    @TypeConverter
    fun toPriority(priority: Priority): String {
        return priority.name
    }
}