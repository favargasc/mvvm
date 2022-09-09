package com.example.mvvm.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.navigation.AppScreens

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun ShoppingCartScreen(navController: NavController) {}
/*
    Column {
        TopAppBar (
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(80.dp),
            title = {
                Box(
                    modifier = Modifier
                        .padding(start = 50.dp)
                ) {
                    Text(
                        text = "Mi carrito",
                        fontWeight = FontWeight.Light,
                        fontSize = 25.sp
                    )
                }
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.MainMenuScreen.route)}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            },
            elevation = 0.dp
        )

/*        MealsListRemove (
            false, 450
        ) {
            ProductContent(
                { ProductImage("") },
                { ProductTitle("") },
                { ProductCost(0.0) }
            )
        }
        Payment()
        OrderButton("Pagar")
    }*/
}*/
/*
@Composable
fun Payment() {
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
                PaymentDetail("₡2300", TextAlign.End)

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
                PaymentDetail("₡8000", TextAlign.End)
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
}*/