package com.example.trip.di

import android.app.Application
import com.example.trip.data.TravelActivityDatabase
import com.example.trip.data.TravelActivityRepository
import com.example.trip.data.TravelActivityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Hilt que define como prover as dependências para a aplicação.
 * As dependências definidas aqui têm escopo de Singleton, ou seja, uma única instância
 * é criada e compartilhada por toda a aplicação.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Prova a instância do banco de dados Room.
     * @param app A instância da [Application], provida automaticamente pelo Hilt.
     * @return Uma instância singleton de [TravelActivityDatabase].
     */
    @Provides
    @Singleton
    fun provideTravelActivityDatabase(app: Application): TravelActivityDatabase {
        return TravelActivityDatabase.getInstance(app)
    }

    /**
     * Prova a implementação do repositório [TravelActivityRepository].
     * @param db A instância do banco de dados, provida pelo método acima.
     * @return Uma instância singleton de [TravelActivityRepository].
     */
    @Provides
    @Singleton
    fun provideTravelActivityRepository(db: TravelActivityDatabase): TravelActivityRepository {
        return TravelActivityRepositoryImpl(db.dao)
    }
}