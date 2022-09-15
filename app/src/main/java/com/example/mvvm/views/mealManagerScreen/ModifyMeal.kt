package com.example.mvvm.views.mealManagerScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.models.Meal
import com.example.mvvm.models.Time
import com.example.mvvm.models.Type
import com.example.mvvm.navigation.AppScreens
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.FormEntry
import com.example.mvvm.views.mealManagerScreen.components.BottomBar
import com.example.mvvm.views.mealManagerScreen.components.ComboBoxTime
import com.example.mvvm.views.mealManagerScreen.components.ComboBoxType
import com.example.mvvm.views.userManagerScreen.components.TopBar
import java.util.*

@ExperimentalMaterialApi
@Composable
fun ModifyMeal(
    mealViewModel: MealViewModel,
    navigateToMealManager: () -> Unit,
    mealId: String
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (cost, setCost) = remember { mutableStateOf("") }
    val (img, setImg) = remember { mutableStateOf("") }
    var availability by remember { mutableStateOf(true) }
    val (type, setType) = remember { mutableStateOf(Type.MAIN) }
    val (time, setTime) = remember { mutableStateOf(Time.BREAKFAST) }

    Scaffold(
        topBar = { TopBar("Editor de Comidas", navigateToMealManager) },
    ) {
        Column(
            Modifier.padding(vertical = 50.dp, horizontal = 44.dp)
        ) {
            FormEntry("Nombre", name, setName)
            FormEntry("Costo", cost, setCost)
            FormEntry("Imagen", img, setImg)

            Row {
                Text(
                    text = "Disponibilidad",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 28.dp)
                )
                Switch(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 120.dp),
                    checked = availability,
                    onCheckedChange = { availability = !availability })
            }

            Row {
                ComboBoxType(
                    labelText = "Tipo",
                    state = type.name,
                    setType
                )

                ComboBoxTime(
                    labelText = "Tiempo",
                    state = time.name,
                    setTime
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF39439D)
                ),
                onClick = {
                    val resCost = if (cost != "") cost.toDouble() else 0.0
                    val meal =
                        Meal(mealId, name, availability, type.ordinal, time.ordinal, resCost, img)
                    mealViewModel.modifyMeal(meal, mealId)
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
}