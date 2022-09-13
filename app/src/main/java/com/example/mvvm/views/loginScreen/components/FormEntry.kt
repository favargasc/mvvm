package com.example.mvvm.views.loginScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.views.BlueInput
import com.example.mvvm.views.FormLabel

@Composable
fun FormEntry(
    text: String,
    state: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

        OutlinedTextField(
            value = state,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0F5FE),
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color(0xFFF1F1FE)
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .height(52.dp)
        )
    }
}