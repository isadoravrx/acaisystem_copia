package com.example.lounge.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lounge.R
import com.example.lounge.ui.theme.components.BottomTotalButton
import com.example.lounge.ui.theme.components.ItemBox

@Composable
fun SideDishes(navController: NavHostController) {
    var selectedItemsCount by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0.00) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Seção para a imagem e título
            HeaderSectionSideDishes(onBackClick = {
                navController.navigate("home")
            })

            // Seção para a lista de opções
            ItemSelectionSection(
                selectedItemsCount = selectedItemsCount,
                onItemAdded = {
                    selectedItemsCount++
                    totalPrice += 2.00 // Suponha que cada adição acrescente R$ 2.00
                },
                onItemRemoved = {
                    if (selectedItemsCount > 0) {
                        selectedItemsCount--
                        totalPrice -= 2.00
                    }
                }
            )

            // Seção para o preço total e o botão "Continuar"
            BottomTotalButton(total = "R$ %.2f".format(totalPrice)) {
                // ESPAÇO PARA A PRÓXIMA TELA
            }
        }
    }
}

@Composable
fun HeaderSectionSideDishes(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.side_dishes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun ItemSelectionSection(
    selectedItemsCount: Int,
    onItemAdded: () -> Unit,
    onItemRemoved: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // PARTE QUE RECEBERÁ O PEDIDO DAS TELAS PASSADAS E O VOLUME DO MESMO
        Text(
            text = "Açaí tradicional 250 ml",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = "R$ 20", fontSize = 18.sp, fontWeight = FontWeight.Medium)

        // Lista de itens
        Text(text = "Acompanhamentos $selectedItemsCount/3", fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))

        // Linha dos Acompanhamentos
        ItemRow("Amendoim", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Amendoim Triturado", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Aveia", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Banana", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Bolinhas de Nescau", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Farinha Láctea", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Farinha de Amendoim", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Farinha de Castanha", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Granola", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Leite Condensado", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Leite em pó", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Mel", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
        ItemRow("Sucrilhos", onItemAdded = onItemAdded, onItemRemoved = onItemRemoved)
    }
}

@Composable
fun ItemRow(
    itemName: String,
    onItemAdded: () -> Unit,
    onItemRemoved: () -> Unit
) {
    var itemCount by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = itemName, fontSize = 18.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                if (itemCount > 0) {
                    itemCount--
                    onItemRemoved()
                }
            }) {
                Text(text = "-", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = itemCount.toString(), modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(onClick = {
                itemCount++
                onItemAdded()
            }) {
                Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun BottomTotalButton(total: String, onContinueClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Total: $total",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onContinueClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text(
                text = "Continuar",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
