package com.yassin.financeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.yassin.financeapp.navigation.Screen

@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { navController.navigate(Screen.Konten.route) }) { Text("Konten") }
        Button(onClick = { navController.navigate(Screen.Abos.route) }) { Text("Abos") }
        Button(onClick = { navController.navigate(Screen.Kategorien.route) }) { Text("Kategorien") }
        Button(onClick = { navController.navigate(Screen.Transaktionen.route) }) { Text("Transaktionen") }
        Button(onClick = { navController.navigate(Screen.Sparziele.route) }) { Text("Sparziele") }
    }
}