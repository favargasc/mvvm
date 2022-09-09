package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.MealsIcon

@Composable
fun OptionSelector(options: List<MealsIcon>, type: Int, onTypeChange: (Int) -> Unit) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(Color(0xFFFCFCFC))
        , horizontalArrangement = Arrangement.Center
    ) {
        items(options) { option ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp, 5.dp)
                    .size(60.dp, 50.dp)
                    .selectable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        selected = option.id == type,
                        onClick = {
                            onTypeChange(if (type != option.id) option.id else -1)
                        }
                    ),
                border = if (option.id == type) BorderStroke(1.dp, Color.Black) else BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = rememberAsyncImagePainter(model = option.url),
                        contentDescription = "",
                        tint = if (option.id == type) Color.Black else Color(0xFFA8A8A8)
                    )
                }
            }
        }
    }
}