package com.example.mvvm.views

import android.media.Image
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.Meal
import com.example.mvvm.models.MealsIcon
import com.example.mvvm.navigation.AppNavigation
import com.example.mvvm.navigation.AppScreens

val mealsList = mutableStateListOf(
    Meal(
        "10",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "9",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "8",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "1",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "2",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "3",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "4",
        "Pinto",
        true,
        "Postre",
        10000.0
    ),
    Meal(
        "5",
        "Pinto",
        true,
        "Postre",
        10000.0
    )
)

val mealsIcons = listOf(
    MealsIcon(1, "Principales", "https://img.icons8.com/glyph-neue/344/rice-bowl.png"),
    MealsIcon(2, "Postres", "https://img.icons8.com/glyph-neue/344/ice-cream-bowl.png"),
    MealsIcon(3, "Adicionales", "https://img.icons8.com/ios-filled/344/taco.png"),
    MealsIcon(4, "Bebidas", "https://img.icons8.com/glyph-neue/344/soda-cup.png"),
)

val mealsIcons2 = listOf(
    MealsIcon(1, "Principales", "https://img.icons8.com/ios-filled/344/0800.png"),
    MealsIcon(2, "Postres", "https://img.icons8.com/ios-filled/344/1100.png"),
    MealsIcon(3, "Adicionales", "https://img.icons8.com/ios-filled/344/1500.png"),
    MealsIcon(4, "Adicionales", "https://img.icons8.com/ios-filled/344/1700.png"),
)

@Composable
fun MainMenuScreen(navController: NavController) {
    MainMenuContent(navController)
}

@Composable
fun MainMenuContent(navController: NavController) {
    Column(
        modifier = Modifier.background(Color(0xFFFCFCFC))
    ){
        TopAppBar(
            backgroundColor = Color(0xFFFCFCFC),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(AppScreens.LoginScreen.route)}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            },
            title = {
                Box(
                    modifier = Modifier
                        .padding(start = 70.dp)
                ) {
                    Text(
                        text = "Menú",
                        fontWeight = FontWeight.Light,
                        fontSize = 25.sp
                    )
                }

            },
            actions = {
                IconButton(onClick = { navController.navigate(AppScreens.ShoppingCartScreen.route)}) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp),
                        tint = Color(0xFF000000)
                    )
                }
            },
            elevation = 0.dp,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(80.dp),
        )
        SearchBar()
        MealsTypeFilter(mealsIcons)
        MealsTypeFilter(mealsIcons2)
        MealsList(false, 380) {
            ProductContent(
                { ProductImage() },
                { ProductTitle() },
                { ProductCost() },
                { ProductController() }
            )
        }
        OrderButton("Agregar al carrito")
    }
}

@Composable
fun MealsTypeFilter(listIcons: List<MealsIcon>) {
    val listState = rememberLazyListState()
    var selectedIndex by remember{mutableStateOf(-1)}

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(Color(0xFFFCFCFC))
        , horizontalArrangement = Arrangement.Center
    ) {
        items(listIcons) { icon ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp, 5.dp)
                    .size(60.dp, 50.dp)
                    .selectable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        selected = icon.id == selectedIndex,
                        onClick = {
                            selectedIndex = if (selectedIndex != icon.id) icon.id else -1
                        }
                    ),
                border = if (icon.id == selectedIndex) BorderStroke(1.dp, Color.Black) else BorderStroke(1.dp, Color.White),
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
                        painter = rememberAsyncImagePainter(model = icon.url),
                        contentDescription = "",
                        tint = if (icon.id == selectedIndex) Color.Black else Color(0xFFA8A8A8)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductImage() {
    Image(
        painter = rememberAsyncImagePainter(model = "https://www.freshnlean.com/wp-content/uploads/2021/03/Meal-Plan-plate-keto.png"),
        contentDescription = "",
        modifier = Modifier
            .padding(10.dp)
            .size(110.dp)
    )
}

@Composable
fun ProductTitle() {
    Text(
        text = "Teriyaki chicken",
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
        color = Color(0xFF656565)
    )
}

@Composable
fun ProductCost() {
    Text(
        text ="₡ 3000",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
fun ProductController() {
    var cost by remember { mutableStateOf(0) }
    Card(
        modifier = Modifier
            .padding(start = 80.dp)
            .size(105.dp, 32.dp),
        backgroundColor = (
                if (cost > 0) Color.Black else Color(0xFFA8A8A8)
                ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { cost += 1 }
            ) {
                Icon(
                    Icons.Filled.AddCircle,
                    tint = Color.White,
                    contentDescription = ""
                )
            }

            Text(
                text = "$cost",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            IconButton(
                onClick = {
                    if  (cost >= 1) cost -= 1
                }
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://img.icons8.com/material-rounded/344/minus-sign.png"),
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
    }

}

@Composable
fun ProductContent(
    img: @Composable () -> Unit,
    title: @Composable () -> Unit,
    cost: @Composable () -> Unit,
    controller: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        img()
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            title()
            cost()
            controller()
        }
    }
}

@Composable
fun ProductContent(
    img: @Composable () -> Unit,
    title: @Composable () -> Unit,
    cost: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        img()
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            title()
            cost()
        }
    }
}

@Composable
fun MealsList(
    isFullSize: Boolean,
    size: Int = 0,
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier =
        if (isFullSize)
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCFCFC))

        else Modifier
            .height(size.dp)
            .background(Color(0xFFFCFCFC)),
    ) {
        items(mealsList) { meal ->
            ListItem(meal) { content() }
        }
    }
}
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun MealsListRemove(
    isFullSize: Boolean,
    size: Int = 0,
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier =
            if (isFullSize)
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF9F9F9))

            else Modifier
                .height(size.dp)
                .background(Color(0xFFF9F9F9)),
    ) {
        items(mealsList, { mealsList:Meal->mealsList.ID }) { meal ->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                mealsList.remove(meal)
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
                        ListItem(meal) { content() }
                    }
                }
            )

        }
    }
}


@Composable
fun ListItem (
    meal: Meal,
    content: @Composable() () -> Unit
) {
    var cost by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        content()
    }
}

@Composable
fun OrderButton(text: String) {
    Button(
        modifier = Modifier
            .padding(15.dp, 2.dp)
            .fillMaxWidth()
            .height(60.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF39439D)
        ),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun SearchBar() {
    var search by remember { mutableStateOf("") }

    OutlinedTextField(
        value = search,
        onValueChange = { search = it},
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
}