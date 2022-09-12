package com.example.mvvm.views.userManagerScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.models.Invoice

@Composable
fun UserLogDetail(
    invoice: Invoice
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Row {
                    Text(
                        text = "#${invoice.id}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, end = 65.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = invoice.date,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Light
                    )
                }
                Text(
                    text = "- - - - - - - - - - - - - - - - - - - - -",
                    color = Color(0xFFF3F3F3),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                for (i in invoice.cartMeals) {
                    Text(
                        text = "${i.count}.  ${i.meal?.name}      ₡${i.meal?.cost?.times(i.count)}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                        color = Color(0xFF656565)
                    )
                }
                Text(
                    text = "- - - - - - - - - - - - - - - - - - - - -",
                    color = Color(0xFFF3F3F3),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Total Principales:  ₡${invoice.main}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    color = Color(0xFF656565),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Total:  ₡${invoice.total}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    color = Color(0xFF656565),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}