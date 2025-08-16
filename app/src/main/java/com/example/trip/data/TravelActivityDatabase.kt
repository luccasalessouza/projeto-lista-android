package com.example.trip.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * A classe principal do banco de dados Room para a aplicação.
 * Define a configuração do banco de dados, incluindo as entidades (tabelas) e a versão.
 *
 * @property dao O Data Access Object para interagir com a tabela de atividades.
 */
@Database(entities = [TravelActivityEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TravelActivityDatabase : RoomDatabase() {

    abstract val dao: TravelActivityDao

    /**
     * O companion object fornece um método para obter uma instância singleton do banco de dados.
     * O padrão Singleton garante que apenas uma instância do banco de dados seja criada
     * durante todo o ciclo de vida da aplicação, o que é eficiente e seguro.
     */
    companion object {
        @Volatile
        private var INSTANCE: TravelActivityDatabase? = null

        /**
         * Retorna a instância singleton do [TravelActivityDatabase].
         * Se a instância ainda não existir, ela é criada de forma segura para threads (thread-safe).
         *
         * @param context O contexto da aplicação, usado para criar o banco de dados.
         * @return A instância singleton do banco de dados.
         */
        fun getInstance(context: Context): TravelActivityDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TravelActivityDatabase::class.java,
                    "travel_activity_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}