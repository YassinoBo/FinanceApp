package com.yassin.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yassin.financeapp.R

@Composable
fun KatScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var showDialog by remember { mutableStateOf(false) } //zeigt NumPad-Fenster
        var selectedIcon by remember { mutableStateOf<String?>(null) } //zeigt Text für Kategorie zum Anzeigen für NumPad-Fenster
        var inputText by remember { mutableStateOf("0€") } //zeigt aktuellen Term/Eingabe im NumPad-Fenster
        var noteText by remember { mutableStateOf("") } //zeigt aktuelle Notiz zu Transaktion an
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(title = "Lebensmittel", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                                                                                                                    selectedIcon = "Lebensmittel"
                                                                                                                    showDialog = true })
            Icon(title = "Restaurant", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                                                                                                                    selectedIcon = "Restaurant"
                                                                                                                    showDialog = true })
            Icon(title = "Freizeit", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                                                                                                                selectedIcon = "Freizeit"
                                                                                                                showDialog = true })
            Icon(title = "Verkehr", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                                                                                                                selectedIcon = "Verkehr"
                                                                                                                showDialog = true })
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(title = "Gesundheit", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Gesundheit"
                showDialog = true })
            Icon(title = "Geschenke", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Geschenke"
                showDialog = true })
            Icon(title = "Familie", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Familie"
                showDialog = true })
            Icon(title = "Shopping", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Shopping"
                showDialog = true })
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(title = "Uni", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Uni"
                showDialog = true })
            Icon(title = "Krypto", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Krypto"
                showDialog = true })
            Icon(title = "Streaming", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "Streaming"
                showDialog = true })
            Icon(title = "WIP", icon = painterResource(id = R.drawable.img), modifier = Modifier.clickable{
                selectedIcon = "WIP"
                showDialog = true })
        }

        var switch = true
        //NumPad
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Column {
                        Text(selectedIcon.toString())

                        Text(
                            text = "Ausgabe",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        BasicTextField(
                            value = inputText,
                            onValueChange = {},
                            readOnly = true,
                            textStyle = TextStyle(
                                fontSize = 24.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        OutlinedTextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            placeholder = { Text("Notizen...") }
                        )
                    }
                },
                text = {
                    Column {
                        var numPad: Array<Array<String>> = arrayOf(
                            arrayOf("/", "7", "8", "9", "<"),
                            arrayOf("x", "4", "5", "6", ""),
                            arrayOf("-", "1", "2", "3", ""),
                            arrayOf("+", "€", "0", ",", "")
                        )
                        for (row in numPad.indices) {
                            Row {
                                for (col in numPad[row].indices) {
                                    val value = numPad[row][col]

                                    if (value == "<") {
                                        Button(
                                            onClick = {
                                                if(inputText != "0€"){
                                                    inputText = if (inputText.length > 1) {
                                                        inputText.dropLast(1)
                                                    } else {
                                                        "0€"
                                                    }
                                                }

                                                if(inputText.isEmpty()){
                                                    inputText = "0€"
                                                }
                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text("<")
                                        }
                                    } else if (row == 3 && col == 4) {
                                        Button(
                                            onClick = {
                                                if (inputText == "0€") {
                                                    showDialog = false
                                                } else {
                                                    inputText = TR(inputText)
                                                }
                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            if (inputText == "0€" || inputText.toIntOrNull() != null || inputText.isEmpty()) {
                                                Text("OK")
                                            } else {
                                                Text("=")
                                            }
                                        }
                                    } else {
                                        Button(
                                            onClick = {
                                                if (switch) {
                                                    inputText = ""
                                                }
                                                switch = false

                                                val lastChar = "€"
                                                inputText = inputText.dropLast(1) + value + lastChar

                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(value)
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                confirmButton = {},
            )
        }
    }
}


fun TR(input:String): String {
    val regex = "(?=[+\\-x/])|(?<=[+\\-x/])".toRegex()
    val parts = input.split(regex)
    val a = parts[0]
    val op = parts[1]
    val b = parts[2]
    var result = ""

    if(op.equals("+")){
        result = (a.toInt() + b.dropLast(1).toInt()).toString()
    }
    if(op.equals("-")){
        result = (a.toInt() - b.dropLast(1).toInt()).toString()
    }
    if(op.equals("x")){
        result = (a.toInt() * b.dropLast(1).toInt()).toString()
    }
    if(op.equals("/")){
        result = (a.toInt() / b.dropLast(1).toInt()).toString()
    }
    return result + "€"
}

@Composable
fun Icon(title: String, icon: Painter, modifier: Modifier = Modifier){
    Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        Text(title)
        Image(
            painter = icon,
            contentDescription = title
        )
    }
}