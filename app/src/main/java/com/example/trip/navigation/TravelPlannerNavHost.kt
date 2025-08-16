package com.example.trip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trip.ui.feature.addedit.AddEditTravelActivityScreen
import com.example.trip.ui.feature.list.TravelPlanScreen

/**
 * Define o grafo de navegação principal da aplicação utilizando o Jetpack Navigation Compose.
 * É responsável por mapear as rotas (strings) para os seus respectivos ecrãs (Composables).
 *
 * @param navController O controlador que gere a navegação entre os ecrãs.
 * Por defeito, é criado um novo controlador com `rememberNavController()`.
 */
@Composable
fun TravelPlannerNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "plan_screen") {

        /**
         * A rota para o ecrã principal, que exibe a lista de atividades.
         */
        composable("plan_screen") {
            TravelPlanScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        /**
         * A rota para o ecrã de adicionar ou editar uma atividade.
         * Aceita um argumento 'id' do tipo Int na rota. Se o id for -1, significa
         * que é uma nova atividade; caso contrário, é a edição de uma atividade existente.
         */
        composable(
            route = "add_edit_screen/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditTravelActivityScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}