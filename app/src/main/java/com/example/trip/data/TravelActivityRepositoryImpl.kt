package com.example.trip.data

import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementação do [TravelActivityRepository].
 * É responsável por coordenar a obtenção de dados da fonte de dados local (DAO).
 * @param dao O Data Access Object para interagir com o banco de dados Room.
 */
class TravelActivityRepositoryImpl(
    private val dao: TravelActivityDao
) : TravelActivityRepository {

    override suspend fun insertTravelActivity(travelActivity: TravelActivity) {
        dao.insertTravelActivity(travelActivity.toEntity())
    }

    override suspend fun deleteTravelActivity(travelActivity: TravelActivity) {
        dao.deleteTravelActivity(travelActivity.toEntity())
    }

    override suspend fun getTravelActivityById(id: Int): TravelActivity? {
        return dao.getTravelActivityById(id)?.toDomain()
    }

    override fun getTravelActivities(): Flow<List<TravelActivity>> {
        return dao.getTravelActivities().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}

/**
 * Função de extensão para converter um objeto de domínio [TravelActivity]
 * em uma entidade de banco de dados [TravelActivityEntity].
 */
fun TravelActivity.toEntity(): TravelActivityEntity {
    return TravelActivityEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        lastModified = this.lastModified,
        isCompleted = this.isCompleted,
        priority = this.priority
    )
}

/**
 * Função de extensão para converter uma entidade de banco de dados [TravelActivityEntity]
 * em um objeto de domínio [TravelActivity].
 */
fun TravelActivityEntity.toDomain(): TravelActivity {
    return TravelActivity(
        id = this.id,
        title = this.title,
        description = this.description,
        lastModified = this.lastModified,
        isCompleted = this.isCompleted,
        priority = this.priority
    )
}