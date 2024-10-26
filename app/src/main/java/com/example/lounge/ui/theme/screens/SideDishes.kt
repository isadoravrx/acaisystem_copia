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
fun SideDishes(navController: NavHostController, acaiVolume: Int) {
    val acaiVolume = 200  // Exemplo de volume em mL
    val freeItemsLimit = getFreeItemsLimit(acaiVolume)
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
                onItemAdded = { price ->
                    if (selectedItemsCount < 5){ // Limite máximo de 5 acompanhamentos
                        selectedItemsCount++
                        if (selectedItemsCount > freeItemsLimit) { // Cobrar adicional se exceder o limite
                            totalPrice += price
                        }
                    }
                },
                onItemRemoved = { price ->
                    if (selectedItemsCount > 0) {
                        if (selectedItemsCount > freeItemsLimit) { // Remover custo adicional
                            totalPrice -= price
                        }
                        selectedItemsCount--
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

// Define o limite de acompanhamentos gratuitos com base no volume do açaí
fun getFreeItemsLimit(acaiVolume: Int): Int {
    return when (acaiVolume) {
        200 -> 2
        300 -> 3
        400 -> 4
        500, 750 -> 5
        else -> 0 // Caso não seja um valor reconhecido
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
    freeItemsLimit: Int,
    selectedItemsCount: Int,
    selectedItemsCount = selectedItemsCount,
    onItemAdded = {
        if (selectedItemsCount < freeItemsLimit) {
            selectedItemsCount++
        } else {
            selectedItemsCount++
            totalPrice += 2.00
        }
    },
    onItemRemoved = {
        if (selectedItemsCount > 0) {
            if (selectedItemsCount > freeItemsLimit) {
                totalPrice -= 2.00
            }
            selectedItemsCount--
        }
    }
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

        // Mostra o limite de acompanhamentos e quantos já foram selecionados
        Text(text = "Acompanhamentos $selectedItemsCount/$freeItemsLimit grátis", fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(getSideDishes()) { item ->
                ItemRow(item.name, item.price, onItemAdded, onItemRemoved)
            }
        }
    }
}

// Lista de acompanhamentos com nomes e preços
fun getSideDishes(): List<SideDishItem> {
    return listOf(
        SideDishItem("Amendoim", 2.00),
        SideDishItem("Amendoim Triturado", 2.00),
        SideDishItem("Aveia", 2.50),
        SideDishItem("Banana", 2.50),
        SideDishItem("Bolinhas de Nescau", 3.00),
        SideDishItem("Farinha Láctea", 3.00),
        SideDishItem("Farinha de Amendoim", 2.00),
        SideDishItem("Farinha de Castanha", 3.00),
        SideDishItem("Granola", 2.50),
        SideDishItem("Leite Condensado", 3.00),
        SideDishItem("Leite em pó", 2.50),
        SideDishItem("Mel", 2.50),
        SideDishItem("Sucrilhos", 2.00)
    )
}

data class SideDishItem(val name: String, val price: Double)

@Composable
fun ItemRow(
    itemName: String,
    itemPrice: Double,
    onItemAdded: () -> Unit,
    onItemRemoved: () -> Unit
) {
    var itemCount by remember { mutableStateOf(0) }
    var color by remember { mutableStateOf(Color.Gray) } 

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
                    onItemRemoved(itemPrice)
                    color = if (itemCount > 0) Color.Green else Color.Red 
                }
            }) {
                Text(text = "-", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                text = itemCount.toString(),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            IconButton(onClick = {
                itemCount++
                onItemAdded(itemPrice)
                color = Color.Green
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
