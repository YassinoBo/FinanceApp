package com.yassin.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yassin.financeapp.R
import com.yassin.financeapp.ui.components.NumPadDialog

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
        var rating by remember { mutableStateOf(0) } // 0-10
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
            NumPadDialog(
                selectedIcon = selectedIcon,
                inputText = inputText,
                onInputChange = { inputText = it },
                noteText = noteText,
                onNoteChange = { noteText = it },
                rating = rating,
                onRatingChange = { rating = it },
                onDismiss = { showDialog = false }
            )
        }
    }
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