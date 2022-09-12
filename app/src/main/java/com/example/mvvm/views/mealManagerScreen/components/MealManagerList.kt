package com.example.mvvm.views.mealManagerScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.models.*
import com.example.mvvm.viewmodels.MealListState
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.views.FormEntry
import com.example.mvvm.views.FormLabel
import com.example.mvvm.views.mainMenuScreen.components.ProductDetail
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalMaterialApi
@Composable
fun MealManagerList(
    state: MealListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    mealViewModel: MealViewModel,
    navController: NavController
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
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
                            val id =
                                UUID.randomUUID().toString().replace("-", "").removeRange(8, 32)

                            val imgPref = if (img == "") "https://bit.ly/3RCDN9U" else img
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
                        },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Agregar",
                            fontSize = 18.sp,
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
                    .fillMaxSize()
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