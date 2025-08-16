package com.example.trip.ui.feature.list

import app.cash.turbine.test
import com.example.trip.MainDispatcherRule
import com.example.trip.data.TravelActivityRepository
import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import com.example.trip.ui.UiEvent
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Suite de testes unitários para a classe [TravelPlanViewModel].
 * Estes testes focam na verificação da lógica de tratamento de eventos
 * do ecrã da lista de atividades.
 */
@ExperimentalCoroutinesApi
class TravelPlanViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `ao receber evento de deletar, deve chamar a função de deleção do repositório`() = runTest {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val viewModel = TravelPlanViewModel(repository)
        val activityToDelete = TravelActivity(
            id = 1,
            title = "Teste",
            description = null,
            lastModified = 0L,
            isCompleted = false,
            priority = Priority.MEDIUM
        )
        val event = TravelPlanEvent.OnDeleteClick(activityToDelete)

        // Act
        viewModel.onEvent(event)

        // Assert
        coVerify(exactly = 1) {
            repository.deleteTravelActivity(activityToDelete)
        }
    }

    @Test
    fun `ao receber evento de adicionar, deve emitir evento de navegação para a tela de edição`() = runTest {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val viewModel = TravelPlanViewModel(repository)
        val event = TravelPlanEvent.OnAddClick

        // Act
        viewModel.onEvent(event)

        // Assert
        viewModel.uiEvent.test {
            val emittedEvent = awaitItem()
            assertEquals(UiEvent.Navigate("add_edit_screen/-1"), emittedEvent)
        }
    }

    /**
     * Testa se, ao receber um evento de clique num item, o ViewModel emite o evento
     * de navegação correto para a tela de edição com o ID do item.
     */
    @Test
    fun `ao receber evento de clique no item, deve emitir evento de navegação com o ID correto`() = runTest {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val viewModel = TravelPlanViewModel(repository)
        val activityToEdit = TravelActivity(
            id = 123, // ID de teste
            title = "Item para Editar",
            description = null,
            lastModified = 0L,
            isCompleted = false,
            priority = Priority.MEDIUM
        )
        val event = TravelPlanEvent.OnItemClick(activityToEdit)

        // Act
        viewModel.onEvent(event)

        // Assert
        viewModel.uiEvent.test {
            val emittedEvent = awaitItem()
            assertEquals(UiEvent.Navigate("add_edit_screen/123"), emittedEvent)
        }
    }
}