package com.example.trip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.trip.navigation.TravelPlannerNavHost
import com.example.trip.ui.theme.TripTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * A Activity principal e o ponto de entrada da interface de utilizador da aplicação.
 * É responsável por configurar o conteúdo da UI com Jetpack Compose.
 *
 * A anotação `@AndroidEntryPoint` é necessária para que o Hilt possa injetar
 * dependências nesta Activity e nos Composables que ela contém.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Chamado quando a Activity é criada pela primeira vez.
     * Aqui, o tema da aplicação e o grafo de navegação são configurados.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TravelPlannerNavHost()
                }
            }
        }
    }
}