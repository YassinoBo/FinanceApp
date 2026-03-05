package com.yassin.financeapp.ui.components

import android.widget.NumberPicker
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun LongButton(icon: Painter, title: String, note: String) {
    val icon = icon
    val title = title
    val note = note

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = icon,
            contentDescription = "Button Icon"
        )
        Column {
            Text(title)
            Text(note)
        }
    }
}


@Composable
fun NumPadDialog(
    selectedIcon: String?,
    inputText: String,
    onInputChange: (String) -> Unit,
    noteText: String,
    onNoteChange: (String) -> Unit,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var switch by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
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
                    onValueChange = { onNoteChange(it) },
                    placeholder = { Text("Notizen...") }
                )

                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 0
                            maxValue = 10
                            value = rating
                            wrapSelectorWheel = false
                            setOnValueChangedListener { _, _, newVal ->
                                onRatingChange(newVal)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        },
        text = {
            Column {
                val numPad: Array<Array<String>> = arrayOf(
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
                                        var text = inputText
                                        if (text != "0€") {
                                            text = if (text.length > 1) text.dropLast(1) else "0€"
                                        }
                                        if (text.isEmpty()) text = "0€"
                                        onInputChange(text)
                                    },
                                    modifier = Modifier.weight(1f)
                                ) { Text("<") }
                            } else if (row == 3 && col == 4) {
                                Button(
                                    onClick = {
                                        val text = if (inputText == "0€") {
                                            onDismiss()
                                            inputText
                                        } else {
                                            TR(inputText)
                                        }
                                        onInputChange(text)
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
                                        var text = inputText
                                        if (switch) text = ""
                                        switch = false
                                        val lastChar = "€"
                                        text = text.dropLast(1) + value + lastChar
                                        onInputChange(text)
                                    },
                                    modifier = Modifier.weight(1f)
                                ) { Text(value) }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {},
    )
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