package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Meal
import java.util.*

@Composable
fun ProductDetail(
    meal: Meal,
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.img),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                ,
                contentScale = ContentScale.FillBounds
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = meal.name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    color = Color(0xFF656565)
                )

                Text(
                    text ="₡${meal.cost}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                ProductCounter(count, onCountChange)
            }
        }
    }
}