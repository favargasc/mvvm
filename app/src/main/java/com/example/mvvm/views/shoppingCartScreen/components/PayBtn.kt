package com.example.mvvm.views.shoppingCartScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Invoice
import com.example.mvvm.models.User
import com.example.mvvm.repositories.InvoiceRepository
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.viewmodels.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


@Composable
fun PayBtn(
    text: String,
    orders: SnapshotStateList<CartMeal>,
    invoiceViewModel: InvoiceViewModel,
    userViewModel: UserViewModel,
    userId: String
) {
    val coroutineScope = rememberCoroutineScope()

    Button(
        modifier = Modifier
            .padding(15.dp, 2.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF39439D)
        ),
        onClick = {
            if (orders.size > 0) {
                coroutineScope.launch {
                    val id = UUID.randomUUID().toString().replace("-", "").removeRange(8, 32)
                    val user = userViewModel.getUser(userId)
                    val date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())

                    invoiceViewModel.addNewInvoice(
                        Invoice(
                            id,
                            date.toString(),
                            user,
                            orders
                        )
                    )
                    orders.clear()
                }
            }
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