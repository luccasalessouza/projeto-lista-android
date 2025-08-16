package com.example.trip.ui.feature.addedit

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.trip.MainDispatcherRule
import com.example.trip.data.TravelActivityRepository
import com.example.trip.domain.Priority
import com.example.trip.ui.UiEvent
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class AddEditTravelActivityViewModelTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun `quando o título está em branco, ao salvar, deve emitir evento de snackbar e não chamar o repositório`() =
        runTest {

            val repository = mockk<TravelActivityRepository>(relaxed = true)
            val savedStateHandle = SavedStateHandle()
            val viewModel = AddEditTravelActivityViewModel(repository, savedStateHandle)


            viewModel.onEvent(AddEditTravelActivityEvent.OnSaveClick)


            viewModel.uiEvent.test {

                val emittedEvent = awaitItem()
                assertTrue(emittedEvent is UiEvent.ShowSnackbar)


                assertEquals(
                    "O título não pode ser vazio",
                    (emittedEvent as UiEvent.ShowSnackbar).message
                )


                cancelAndIgnoreRemainingEvents()
            }


            coVerify(exactly = 0) {
                repository.insertTravelActivity(any())
            }
        }


    @Test
    fun `quando os dados são válidos, ao salvar, deve chamar o repositório e emitir evento de navegar para trás`() =
        runTest {

            val repository = mockk<TravelActivityRepository>(relaxed = true)
            val savedStateHandle = SavedStateHandle()
            val viewModel = AddEditTravelActivityViewModel(repository, savedStateHandle)


            viewModel.onEvent(AddEditTravelActivityEvent.OnTitleChange("Visitar o Museu"))
            viewModel.onEvent(AddEditTravelActivityEvent.OnPriorityChange(Priority.HIGH))


            viewModel.onEvent(AddEditTravelActivityEvent.OnSaveClick)


            coVerify(exactly = 1) {
                repository.insertTravelActivity(any())
            }

            viewModel.uiEvent.test {

                assertEquals(UiEvent.NavigateBack, awaitItem())
            }
        }
}