package com.example.mvvm.views

import android.annotation.SuppressLint
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.Meal


@Composable
fun MealsManagerScreen() {
    var showMenu by remember { mutableStateOf(false)}
/*
    Column {
        TopAppBar(
            backgroundColor = Color(0xFF003579),
            title = {
                Text(
                    text = "Menú de platillos",
                    color = Color.White,
                    modifier = Modifier.padding(start = 80.dp)
                )
            },
            actions = {
                IconButton(
                    onClick = { showMenu = !showMenu },
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "",
                        tint = Color.Gray
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Text("Agregar")
                    }
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        )
        {
            items(meals) { meal ->
                MealCard(meal)
            }
        }
    }*/
}

@Composable
fun MealCard(meal: Meal) {
    var backgroundColor: Long = 0xFFE6ECF4

    /*if (!meal.availability) {
        backgroundColor = 0xFFFEE6E5
    }

    Card(
        modifier = Modifier
            .padding(
                start = 20.dp,
                top = 0.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = Color(backgroundColor),
        elevation = 2.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row {
            MealType(meal.type)
            MealBodyCard(meal.ID, meal.name, meal.cost)
            OptionMenu()
        }
    }*/
}

@Composable
fun MealType(mealType: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(90.dp, 100.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://cdn.pixabay.com/photo/2022/01/19/08/16/cake-6949106_960_720.png"),
            contentDescription = "",
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 10.dp,
                    end = 20.dp,
                    bottom = 5.dp
                )
                .size(50.dp)
        )
        Text(
            text = mealType,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MealBodyCard(id: String, name: String, cost: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .size(200.dp, 100.dp),
    ) {
        Text(
            text = "($id) $name",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp),
            fontWeight = FontWeight.Bold
        )
        Text(text = "₡${cost}", textAlign = TextAlign.Center)
    }
}
@Composable
fun OptionMenu() {
    var showMenu by remember { mutableStateOf(false)}

    IconButton(
        onClick = { showMenu = !showMenu },
    ) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "",
            tint = Color.Gray
        )
    }

    DropdownMenu (
        expanded = showMenu,
        onDismissRequest = { showMenu = false },
        offset = DpOffset(x = (200).dp, y = (-85).dp)
    ) {
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text("Modificar")
        }
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text("Eliminar")
        }
    }
}

@Composable
fun AddMealForm() {
    var showModel by remember { mutableStateOf(false)}

}
