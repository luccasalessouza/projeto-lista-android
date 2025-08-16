package com.example.trip

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Uma regra do JUnit que configura o Dispatcher principal das Coroutines para usar um [TestDispatcher]
 * durante os testes. Isto permite que as coroutines sejam executadas de forma controlada e síncrona.
 *
 * @param testDispatcher O dispatcher a ser usado. O padrão é [UnconfinedTestDispatcher], que
 * executa as coroutines imediatamente na thread do teste.
 */
@ExperimentalCoroutinesApi
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}