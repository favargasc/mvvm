package com.example.mvvm.views.shoppingCartScreen.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.CartMeal

@SuppressLint("UnrememberedMutableState")
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun OrderList(
    orders: SnapshotStateList<CartMeal>,
    setMainCost: (Double) -> Unit,
    setTotalCost: (Double) -> Unit
) {
    setMainCost(orders.asSequence().filter { it.meal?.type == 0 }
        .sumOf { it.meal?.cost?.times(it.count)!! })
    setTotalCost(orders.sumOf { it.meal?.cost?.times(it.count)!! })

    LazyColumn(
        modifier = Modifier
            .height(450.dp)
            .background(Color(0xFFF9F9F9)),
    ) {
        items(items = orders, key = { it.id }) { meal ->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                orders.remove(meal)
            }

            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .padding(5.dp),
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color(0xFFFFEEF0)
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(model = "https://img.icons8.com/pastel-glyph/344/trash.png"),
                            contentDescription = "Delete Icon",
                            tint = Color(0xFFDE7884),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(35.dp)
                        )
                    }
                },
                dismissContent = {
                    Card(
                        elevation = 0.dp
                    ) {
                        OrderDetail(meal)
                    }
                }
            )
        }
    }

}
