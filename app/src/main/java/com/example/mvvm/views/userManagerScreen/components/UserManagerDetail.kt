package com.example.mvvm.views.userManagerScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.example.mvvm.viewmodels.MealViewModel
import com.example.mvvm.viewmodels.UserViewModel
import io.grpc.Context

@Composable
fun UserManagerDetail(
    user: User,
    userViewModel: UserViewModel,
    navController: NavController
) {
    var showMenu1 by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = "https://i.ibb.co/0Jmshvb/no-image.png"),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(110.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds,

                )

            Column(
                Modifier
                    .width(190.dp)
                    .padding(start = 5.dp, top = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = user.fullName + " " + user.lastName + " " + user.maidenName,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
                    color = Color(0xFF34495E),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = user.dsi,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    color = Color(0xFFB1BFC6)
                )
            }

            IconButton(
                onClick = { showMenu1 = true },
                modifier = Modifier.padding(bottom = 90.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                )
                DropdownMenu(
                    expanded = showMenu1,
                    onDismissRequest = { showMenu1 = false },
                    offset = DpOffset(x = (10).dp, y = (-30).dp)
                ) {
                    DropdownMenuItem(onClick = {
                        showMenu1 = false
                        navController.navigate("modify_user/${user.studentEmail}")
                    }) {
                        Text("Editar Usuario")
                    }

                    DropdownMenuItem(onClick = {
                        userViewModel.removeUser(user)
                        showMenu1 = false
                    }) {
                        Text("Eliminar Usuario")
                    }
                    DropdownMenuItem(onClick = {
                        showMenu1 = false
                        navController.navigate("user_log/${user.studentEmail}")
                    }) {
                        Text("Historial")
                    }
                }
            }
        }
    }
}