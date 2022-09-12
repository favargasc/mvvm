package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.Meal
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.mainMenuScreen.components.ProductCounter
import kotlinx.coroutines.launch

@Composable
fun MealManagerDetail(
    meal: Meal,
    mealViewModel: MealViewModel,
    navController: NavController
) {

    var showMenu1 by remember { mutableStateOf(false) }
    var showMenu2 by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row {

            Image(
                painter = rememberAsyncImagePainter(model = meal.img),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(
                Modifier
                    .padding(start = 5.dp, top = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "#${meal.id} ",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color(0xFF656565),
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "${meal.name}           ",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = (
                                when (meal.type) {
                                    0 -> {
                                        "Principal"
                                    }
                                    1 -> {
                                        "Postre"
                                    }
                                    2 -> {
                                        "Acompañamiento"
                                    }
                                    else -> {
                                        "Bebida"
                                    }
                                }
                                ),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = Color(0xFF656565)
                    )
                }
                Row {
                    Text(
                        text = "₡${meal.cost}        ",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (meal.availability) "Disponible" else "No Disponible",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = Color(0xFF656565)
                    )
                }
            }

            IconButton(
                onClick = { showMenu1 = true },
                modifier = Modifier.padding(bottom = 90.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )

                DropdownMenu(
                    expanded = showMenu1,
                    onDismissRequest = { showMenu1 = false },
                    offset = DpOffset(x = (10).dp, y = (-30).dp)
                ) {
                    DropdownMenuItem(onClick = {
                        navController.navigate("modify_meal/${meal.id}")
                        showMenu1 = false
                    }) {
                        Text("Editar Comida")
                    }

                    DropdownMenuItem(onClick = {
                        mealViewModel.removeMeal(meal)
                        showMenu1 = false
                    }) {
                        Text("Eliminar Comida")
                    }
                }
            }
        }
    }
}