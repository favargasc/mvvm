package com.example.mvvm.views.invoicesManagerScreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.models.Meal
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.InvoiceViewModel
import com.example.mvvm.views.DatePickerForm
import com.example.mvvm.views.FormEntry
import com.example.mvvm.views.FormLabel
import com.example.mvvm.views.setAge
import com.example.mvvm.views.userManagerScreen.components.TopBar
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModifyInvoice(
    invoiceViewModel: InvoiceViewModel,
    navigateToInvoiceManager: () -> Unit,
    context: Context,
    invoiceId: String
) {
    val (id, setId) = remember { mutableStateOf("") }
    val (date, setDate) = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar("Modificador de Pedidos", navigateToInvoiceManager)
        FormEntry("Número de pedido", id, setId)
        FormEntryI("Fecha de nacimiento", context, date, setDate)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 42.dp, end = 42.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF39439D)
            ),
            onClick = {
                invoiceViewModel.modifyInvoice(id, date, invoiceId)
            },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Modificar",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormEntryI(labelText: String, context: Context, date: String, onDateChange: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        FormLabel(labelText)
        DatePickerFormI(context, date, onDateChange)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerFormI(context: Context, date: String, onDateChange: (String) -> Unit) {
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val time = SimpleDateFormat("HH:mm:ss").format(Date())

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker,
          y: Int,
          m: Int,
          dayOfMonth: Int ->
            onDateChange("$dayOfMonth/${m + 1}/$y ${time}")
        }, year, month, day
    )
    OutlinedTextField(
        value = date,
        enabled = false,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF1F1FE),
            focusedBorderColor = Color(0xFF39439D),
            unfocusedBorderColor = Color(0xFFF1F1FE)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(52.dp)
            .clickable(onClick = { datePickerDialog.show() })
    )
}