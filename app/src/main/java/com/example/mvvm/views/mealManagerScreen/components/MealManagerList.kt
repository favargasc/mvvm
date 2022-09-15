package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.models.Meal
import com.example.mvvm.models.MealSelectable
import com.example.mvvm.models.Time
import com.example.mvvm.models.Type
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealSelectableListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.FormEntry
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.*

@ExperimentalMaterialApi
@Composable
fun MealManagerList(
    state: MealListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
    mealViewModel: MealViewModel,
    navController: NavController
) {
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetContent = {
            val (name, setName) = remember { mutableStateOf("") }
            val (cost, setCost) = remember { mutableStateOf("") }
            val (img, setImg) = remember { mutableStateOf("") }
            var availability by remember { mutableStateOf(true) }

            val (type, setType) = remember { mutableStateOf(Type.MAIN) }
            val (time, setTime) = remember { mutableStateOf(Time.BREAKFAST) }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier
                        .padding(vertical = 30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FormEntry("Nombre", name, setName)
                    FormEntry("Costo", cost, setCost)
                    FormEntry("Imagen", img, setImg)

                    Row(Modifier.padding(end = 100.dp)) {
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
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .height(60.dp)
                            .width(269.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF0069FE)
                        ),
                        onClick = {
                            val id =
                                UUID.randomUUID().toString().replace("-", "").removeRange(8, 32)

                            val imgPref =
                                if (img == "") "https://i.ibb.co/0Jmshvb/no-image.png" else img
                            mealViewModel.addNewMeal(
                                Meal(
                                    id,
                                    name,
                                    availability,
                                    type.ordinal,
                                    time.ordinal,
                                    cost.toDouble(),
                                    imgPref
                                )
                            )
                        }
                    ) {
                        Text(
                            text = "Agregar",
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }

                }
            }


        }
    ) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = refreshData) {
            LazyColumn(
                modifier = Modifier
                    .height(580.dp)
                    .background(Color(0xFFFCFCFC)),
            ) {
                items(
                    items = state.meals
                ) { meal ->
                    MealManagerDetail(meal, mealViewModel, navController)
                }
            }
        }
    }
}