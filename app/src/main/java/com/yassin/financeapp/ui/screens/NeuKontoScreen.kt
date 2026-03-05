package com.yassin.financeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yassin.financeapp.R
import com.yassin.financeapp.navigation.Screen
import com.yassin.financeapp.ui.components.LongButton
import com.yassin.financeapp.ui.components.TR

@Composable
fun NeuKontoScreen(navController: NavHostController) {


    Column {
        Row { Text("Neues Konto") }

        var noteText by remember { mutableStateOf("") } //zeigt aktuellen Namen des Kontos
        Row {
            OutlinedTextField(
                value = noteText,
                onValueChange = { },
                label = { Text("Name") }
            )
        }
        Row {
            Button(
                onClick = { navController.navigate(Screen.Konten.route) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("X")
            }
        }

        Row {
            Button(
                onClick = { navController.navigate(Screen.Konten.route) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Fertig")
            }
        }

        var showDialog by remember { mutableStateOf(false) }
        Row {
            Text("Konto")
        }
        Row {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Hintergrund
                    contentColor = Color.Black          // Textfarbe
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                LongButton(
                    icon = painterResource(id = R.drawable.img),
                    "Typ",
                    "Regulär"
                ) //Hier "Regulär" ersetzen mit aktuell ausgewählten
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Column {
                            Text("Neues Konto")
                            Row {
                                Button(
                                    onClick = {
                                        navController.navigate(Screen.NeuKonten.route)
                                        showDialog = false
                                    },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text("Regulär")
                                }
                            }
                            Row {
                                Button(
                                    onClick = { showDialog = true },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text("Ersparnisse")
                                }
                            }
                            Row {
                                Button(
                                    onClick = { showDialog = true },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text("Schulden")
                                }
                            }
                        }
                    },
                    text = {},
                    confirmButton = {},
                )
            }
        }

        Row {
            Text("Betrag")
        }
        var inputText by remember { mutableStateOf("0€") } //zeigt aktuellen Term/Eingabe im NumPad-Fenster
        Row {
            Button(
                onClick = {TR(inputText)},
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Hintergrund
                    contentColor = Color.Black          // Textfarbe
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Kontostand")

                    Text(inputText)
                }
            }

        }
    }
}