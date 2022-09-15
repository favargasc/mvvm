package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun MealManagerDetail(
    meal: Meal,
    mealViewModel: MealViewModel,
    navController: NavController
) {

    var showMenu1 by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.img),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds,
            )
            Column(
                Modifier
                    .padding(horizontal = 5.dp)
                    .width(80.dp)
            ) {
                Text(
                    text = meal.name,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color(0xFF34495E),
                    fontWeight = FontWeight.SemiBold
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
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color(0xFFB1BFC6)
                )
            }
            Column(
                Modifier
                    .padding(horizontal = 5.dp)
                    .width(95.dp)) {
                Text(
                    text = "₡${meal.cost}",
                    fontSize = 19.sp,
                    modifier = Modifier.padding(top = 27.dp, bottom = 5.dp),
                    color = Color(0xFF34495E),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = if (meal.availability) "Disponible" else "Insuficiente",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color(0xFFB1BFC6)
                )
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