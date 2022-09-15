package com.example.mvvm.views.shoppingCartScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Payment(
    mainCost: Double,
    totalCost: Double
) {
    Box(
        Modifier
            .padding(
                top = 5.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp,
            )
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row {
                PaymentDetail("Principales", TextAlign.Start)
                PaymentDetail("₡${mainCost}", TextAlign.End)

            }
            Text(
                text = "- - - - - - - - - - - - - - - - - - - -",
                color = Color(0xFFF3F3F3),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row {
                PaymentDetail("Total", TextAlign.Start)
                PaymentDetail("₡${totalCost}", TextAlign.End)
            }
        }
    }
}

@Composable
fun PaymentDetail(text: String, align: TextAlign) {
    Text(
        text = text,
        textAlign = align,
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp,
        modifier = Modifier
            .width(180.dp)
    )
}