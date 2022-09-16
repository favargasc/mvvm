package com.example.mvvm.views.invoicesManagerScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.models.Invoice
import com.example.mvvm.viewmodels.InvoiceViewModel

@Composable
fun InvoiceDetail(
    invoice: Invoice,
    invoiceViewModel: InvoiceViewModel,
    navController: NavController
) {
    var showMenu1 by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Row {
                    Text(
                        text = "#${invoice.id}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, end = 50.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = invoice.date,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Light
                    )

                    IconButton(
                        onClick = { showMenu1 = true },
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
                                navController.navigate("modify_invoice/${invoice.id}")
                            }) {
                                Text("Editar Pedido")
                            }

                            DropdownMenuItem(onClick = {
                                invoiceViewModel.removeInvoice(invoice)
                                showMenu1 = false
                            }) {
                                Text("Eliminar Pedido")
                            }
                        }
                    }
                }
                Row {
                    Text(
                        text = "${invoice.user?.dni}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, end = 50.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${invoice.user?.fullName} ${invoice.user?.lastName} ${invoice.user?.maidenName}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color(0xFF656565)
                    )
                }
                Row {
                    Text(
                        text = "${invoice.user?.dsi}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, end = 50.dp),
                        color = Color(0xFF656565),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${invoice.user?.studentEmail}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color(0xFF656565)
                    )
                }
                Text(
                    text = "- - - - - - - - - - - - - - - - - - - - -",
                    color = Color(0xFFF3F3F3),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                for (i in invoice.cartMeals) {
                    Text(
                        text = "${i.count}.  ${i.meal?.name}      ₡${i.meal?.cost?.times(i.count)}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                        color = Color(0xFF656565)
                    )
                }
                Text(
                    text = "- - - - - - - - - - - - - - - - - - - - -",
                    color = Color(0xFFF3F3F3),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Total Principales:  ₡${invoice.main}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    color = Color(0xFF656565),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Total:  ₡${invoice.total}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    color = Color(0xFF656565),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}