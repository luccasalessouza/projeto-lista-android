package com.example.trip.data

import com.example.trip.MainDispatcherRule
import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * Suite de testes unitários para a classe [TravelActivityRepositoryImpl].
 * Estes testes validam se o repositório está a interagir corretamente com o DAO
 * e a mapear os dados entre as camadas de domínio e de dados.
 */
@ExperimentalCoroutinesApi
class TravelActivityRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    /**
     * Testa se a função `insertTravelActivity` do repositório chama o DAO
     * com a entidade corretamente mapeada.
     *
     * **Cenário:**
     * - **Dado que** um objeto de domínio [TravelActivity] é fornecido.
     * - **Quando** a função `insertTravelActivity` do repositório é chamada.
     * - **Então** a função `insertTravelActivity` do DAO deve ser chamada
     * com um objeto [TravelActivityEntity] contendo os mesmos dados.
     */
    @Test
    fun `ao inserir atividade, deve chamar o dao com a entidade mapeada corretamente`() = runTest {
        // Arrange (Organizar)
        val dao = mockk<TravelActivityDao>(relaxed = true)
        val repository = TravelActivityRepositoryImpl(dao)
        val travelActivityDomain = TravelActivity(
            id = 1,
            title = "Teste",
            description = "Desc",
            lastModified = 123L,
            isCompleted = false,
            priority = Priority.HIGH
        )

        // Act (Agir)
        repository.insertTravelActivity(travelActivityDomain)

        // Assert (Verificar)
        coVerify(exactly = 1) {
            dao.insertTravelActivity(travelActivityDomain.toEntity())
        }
    }
}