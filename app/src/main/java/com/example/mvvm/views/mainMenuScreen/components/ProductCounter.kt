package com.example.mvvm.views.mainMenuScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.CartMeal

@Composable
fun ProductCounter(
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 80.dp)
            .size(105.dp, 32.dp),
        backgroundColor = (
            if (count > 0) Color.Black else Color(0xFFA8A8A8)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onCountChange(count + 1)
                }
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    tint = Color.White,
                    contentDescription = ""
                )
            }

            Text(
                text = "$count",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            IconButton(
                onClick = {
                    if  (count >= 1) onCountChange(count - 1)
                }
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://bit.ly/3KXVuhq"),
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
    }

}
