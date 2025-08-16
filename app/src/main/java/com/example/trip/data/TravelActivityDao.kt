package com.example.trip.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) para a entidade [TravelActivityEntity].
 * Define os métodos de acesso ao banco de dados para operações de CRUD (Create, Read, Update, Delete).
 */
@Dao
interface TravelActivityDao {

    /**
     * Insere ou atualiza uma atividade no banco de dados.
     * Se uma atividade com o mesmo `id` já existir, ela será substituída.
     * @param entity A entidade [TravelActivityEntity] a ser inserida.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTravelActivity(entity: TravelActivityEntity)

    /**
     * Deleta uma atividade do banco de dados.
     * @param entity A entidade [TravelActivityEntity] a ser deletada.
     */
    @Delete
    suspend fun deleteTravelActivity(entity: TravelActivityEntity)

    /**
     * Busca uma atividade pelo seu ID.
     * @param id O ID da atividade a ser buscada.
     * @return A [TravelActivityEntity] correspondente ou nulo se não for encontrada.
     */
    @Query("SELECT * FROM TravelActivityEntity WHERE id = :id")
    suspend fun getTravelActivityById(id: Int): TravelActivityEntity?

    /**
     * Retorna um Flow com a lista de todas as atividades, ordenadas.
     * A ordenação é feita primeiro por prioridade (Alta > Média > Baixa)
     * e depois pela data de modificação mais recente.
     * @return Um [Flow] que emite a lista de [TravelActivityEntity] sempre que os dados mudam.
     */
    @Query("""
        SELECT * FROM TravelActivityEntity 
        ORDER BY 
            CASE priority 
                WHEN 'HIGH' THEN 1 
                WHEN 'MEDIUM' THEN 2 
                WHEN 'LOW' THEN 3 
            END, 
            lastModified DESC
    """)
    fun getTravelActivities(): Flow<List<TravelActivityEntity>>
}