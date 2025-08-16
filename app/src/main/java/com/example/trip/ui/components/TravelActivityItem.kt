package com.example.trip.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trip.domain.Priority
import com.example.trip.domain.TravelActivity
import com.example.trip.ui.feature.list.TravelPlanEvent
import java.text.SimpleDateFormat
import java.util.*

/**
 * Um Composable que exibe um único item da lista de atividades.
 * Mostra o título, descrição (se houver), data de modificação e um indicador de prioridade.
 *
 * @param todo O objeto [TravelActivity] a ser exibido.
 * @param onEvent Callback para enviar eventos de volta ao ViewModel (ex: clique no botão de deletar).
 * @param modifier O [Modifier] a ser aplicado ao componente.
 */
@Composable
fun TravelActivityItem(
    todo: TravelActivity,
    onEvent: (TravelPlanEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val priorityColor = when (todo.priority) {
        Priority.HIGH -> Color.Red.copy(alpha = 0.7f)
        Priority.MEDIUM -> Color.Yellow.copy(alpha = 0.7f)
        Priority.LOW -> Color.Green.copy(alpha = 0.7f)
    }

    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(10.dp).background(priorityColor, shape = MaterialTheme.shapes.small))
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = todo.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (!todo.description.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = todo.description)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Modificado em: ${todo.lastModified.toFormattedDate()}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        IconButton(onClick = { onEvent(TravelPlanEvent.OnDeleteClick(todo)) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Deletar")
        }
    }
}

/**
 * Função de extensão para formatar um timestamp Long em uma String de data legível (dd/MM/yyyy HH:mm).
 */
fun Long.toFormattedDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}