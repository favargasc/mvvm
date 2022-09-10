package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.models.CartMeal
import com.example.mvvm.viewmodels.InvoiceViewModel

@Composable
fun OrderBtn(
    text: String,
    orders: SnapshotStateList<CartMeal>,
    ordersTemp: SnapshotStateList<CartMeal>,
) {
    Button(
        modifier = Modifier
            .padding(15.dp, 2.dp)
            .fillMaxWidth()
            .height(60.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF39439D)
        ),
        onClick = {
            orders.addAll(ordersTemp)
        },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}