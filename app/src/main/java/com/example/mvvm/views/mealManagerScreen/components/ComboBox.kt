package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.mvvm.models.Time
import com.example.mvvm.models.Type
import com.example.mvvm.views.FormLabel

@Composable
fun ComboBoxTime(
    labelText: String,
    state: String,
    onTimeChange: (Time) -> Unit,
) {
    val (show, setShow) = remember { mutableStateOf(false) }

    Column {
        FormLabel(labelText)
        BlueFieldBox(state, setShow)
    }
    DropdownMenu(
        expanded = show,
        onDismissRequest = { setShow(false) },
        offset = DpOffset(x = (142).dp, y = (900).dp)
    ) {
        DropdownMenuItem(onClick = {
            onTimeChange(Time.BREAKFAST)
            setShow(false)
        }) {
            Text("Desayuno")
        }

        DropdownMenuItem(onClick = {
            onTimeChange(Time.LUNCH)
            setShow(false)
        }) {
            Text("Almuerzo")
        }

        DropdownMenuItem(onClick = {
            onTimeChange(Time.AFTERNOON_TEA)
            setShow(false)
        }) {
            Text("Media Tarde")
        }

        DropdownMenuItem(onClick = {
            onTimeChange(Time.DINNER)
            setShow(false)
        }) {
            Text("Cena")
        }
    }
}

@Composable
fun ComboBoxType(
    labelText: String,
    state: String,
    onTypeChange: (Type) -> Unit,
) {
    val (show, setShow) = remember { mutableStateOf(false) }

    Column {
        FormLabel(labelText)
        BlueFieldBox(state, setShow)
    }

    DropdownMenu(
        expanded = show,
        onDismissRequest = { setShow(false) },
        offset = DpOffset(x = (0).dp, y = (900).dp)
    ) {
        DropdownMenuItem(onClick = {
            onTypeChange(Type.MAIN)
            setShow(false)
        }) {
            Text("Plato Principal")
        }

        DropdownMenuItem(onClick = {
            onTypeChange(Type.DESSERT)
            setShow(false)
        }) {
            Text("Postre")
        }

        DropdownMenuItem(onClick = {
            onTypeChange(Type.SNACK)
            setShow(false)
        }) {
            Text("Acompañante")
        }

        DropdownMenuItem(onClick = {
            onTypeChange(Type.DRINK)
            setShow(false)
        }) {
            Text("Bebida")
        }
    }
}

@Composable
fun BlueFieldBox(state: String, onChangeOpen: (Boolean) -> Unit) {

    var text = ""
    when (state) {
        "MAIN" -> {
            text = "Principal"
        }
        "DESSERT" -> {
            text = "Postre"
        }
        "SNACK" -> {
            text = "Acompañante"
        }
        "DRINK" -> {
            text = "Bebida"
        }
        "BREAKFAST" -> {
            text = "Desayuno"
        }
        "LUNCH" -> {
            text = "Almuerzo"
        }
        "AFTERNOON_TEA" -> {
            text = "Media Tarde"
        }
        "DINNER" -> {
            text = "Cena"
        }
    }

    OutlinedTextField(
        value = text,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF0F5FE),
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color(0xFFF1F1FE)
        ),
        enabled = false,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(end = 5.dp)
            .height(52.dp)
            .width(134.dp)
            .clickable(
                onClick = { onChangeOpen(true) }
            )
    )
}