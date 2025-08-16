package com.example.trip.ui

/**
 * Define eventos de UI que são de consumo único (one-shot).
 * Usado para ações como navegação ou exibição de Snackbars, que não devem
 * ser re-executadas se a UI for recomposta (ex: ao girar a tela).
 */
sealed class UiEvent {
    /**
     * Evento para navegar para a tela anterior.
     */
    object NavigateBack : UiEvent()

    /**
     * Evento para navegar para uma rota específica.
     * @param route A rota de destino para a navegação.
     */
    data class Navigate(val route: String) : UiEvent()

    /**
     * Evento para exibir uma mensagem em um Snackbar.
     * @param message O texto principal a ser exibido.
     * @param action O texto opcional para um botão de ação no Snackbar.
     */
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ) : UiEvent()
}