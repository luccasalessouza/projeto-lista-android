package com.example.trip.ui.feature.addedit

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.trip.MainDispatcherRule
import com.example.trip.data.TravelActivityRepository
import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import com.example.trip.ui.UiEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Suite de testes unitários para a classe [AddEditTravelActivityViewModel].
 * Estes testes verificam a lógica de negócio do ViewModel de forma isolada,
 * utilizando a biblioteca MockK para simular o comportamento do repositório.
 */
@ExperimentalCoroutinesApi
class AddEditTravelActivityViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `quando o título está em branco, ao salvar, deve emitir evento de snackbar e não chamar o repositório`() = runTest {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val savedStateHandle = SavedStateHandle()
        val viewModel = AddEditTravelActivityViewModel(repository, savedStateHandle)

        // Act
        viewModel.onEvent(AddEditTravelActivityEvent.OnSaveClick)

        // Assert
        viewModel.uiEvent.test {
            val emittedEvent = awaitItem()
            assertTrue(emittedEvent is UiEvent.ShowSnackbar)
            assertEquals("O título não pode ser vazio", (emittedEvent as UiEvent.ShowSnackbar).message)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 0) {
            repository.insertTravelActivity(any())
        }
    }

    @Test
    fun `quando os dados são válidos, ao salvar, deve chamar o repositório e emitir evento de navegar para trás`() = runTest {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val savedStateHandle = SavedStateHandle()
        val viewModel = AddEditTravelActivityViewModel(repository, savedStateHandle)
        viewModel.onEvent(AddEditTravelActivityEvent.OnTitleChange("Visitar o Museu"))
        viewModel.onEvent(AddEditTravelActivityEvent.OnPriorityChange(Priority.HIGH))

        // Act
        viewModel.onEvent(AddEditTravelActivityEvent.OnSaveClick)

        // Assert
        coVerify(exactly = 1) {
            repository.insertTravelActivity(any())
        }
        viewModel.uiEvent.test {
            assertEquals(UiEvent.NavigateBack, awaitItem())
        }
    }

    @Test
    fun `ao inicializar com um ID válido, deve carregar os dados da atividade`() = runTest {
        // Arrange
        val activityId = 1
        val existingActivity = TravelActivity(
            id = activityId,
            title = "Atividade Existente",
            description = "Descrição da Atividade",
            lastModified = System.currentTimeMillis(),
            isCompleted = false,
            priority = Priority.LOW
        )
        val repository = mockk<TravelActivityRepository>()
        coEvery { repository.getTravelActivityById(activityId) } returns existingActivity
        val savedStateHandle = SavedStateHandle(mapOf("id" to activityId))

        // Act
        val viewModel = AddEditTravelActivityViewModel(repository, savedStateHandle)

        // Assert
        assertEquals(existingActivity.title, viewModel.title)
        assertEquals(existingActivity.description, viewModel.description)
        assertEquals(existingActivity.priority, viewModel.priority)
    }

    /**
     * Testa se o estado do título no ViewModel é atualizado corretamente
     * ao receber o evento OnTitleChange.
     */
    @Test
    fun `ao receber evento de mudança de título, o estado do título deve ser atualizado`() {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val viewModel = AddEditTravelActivityViewModel(repository, SavedStateHandle())
        val newTitle = "Novo Título"

        // Act
        viewModel.onEvent(AddEditTravelActivityEvent.OnTitleChange(newTitle))

        // Assert
        assertEquals(newTitle, viewModel.title)
    }

    /**
     * Testa se o estado da prioridade no ViewModel é atualizado corretamente
     * ao receber o evento OnPriorityChange.
     */
    @Test
    fun `ao receber evento de mudança de prioridade, o estado da prioridade deve ser atualizado`() {
        // Arrange
        val repository = mockk<TravelActivityRepository>(relaxed = true)
        val viewModel = AddEditTravelActivityViewModel(repository, SavedStateHandle())
        val newPriority = Priority.HIGH

        // Act
        viewModel.onEvent(AddEditTravelActivityEvent.OnPriorityChange(newPriority))

        // Assert
        assertEquals(newPriority, viewModel.priority)
    }
}