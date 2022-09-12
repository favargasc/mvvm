package com.example.mvvm.views.mainMenuScreen.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar():String {
    var search by remember { mutableStateOf("") }

    OutlinedTextField(
        value = search,
        onValueChange = { search = it },
        modifier = Modifier
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 10.dp,
                bottom = 5.dp
            )
            .height(55.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFFE9E9E9),
            focusedBorderColor = Color(0xFFE9E9E9)
        ),
        leadingIcon = {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = Color(0xFFA8A8A8)
                )
            }
        },
        placeholder = ({
            Text(
                text = "Buscar",
                color = Color(0xFFA8A8A8)
            )
        })
    )
    return search
}