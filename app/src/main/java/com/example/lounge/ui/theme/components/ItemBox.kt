package com.example.lounge.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lounge.R

@Composable
fun ItemBox() {
    // Estado que mantém o item selecionado
    val (selectedItem, setSelectedItem) = remember { mutableStateOf<String?>(null) }

    // Lista de itens
    val items = listOf(
        ItemData(R.drawable.acai, "Açaí"),
        ItemData(R.drawable.cupuacu, "Cupuacu"),
        ItemData(R.drawable.cupuacu_e_acai, "Cupuacu e açaí"),
        ItemData(R.drawable.vitamina, "Suco de açaí"),
        ItemData(R.drawable.vitamina_1, "Vitamina de açaí")
    )

    // Layout de exibição dos itens
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Linha 1
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.take(3).forEach { item ->
                ItemCard(
                    imageResId = item.imageResId,
                    description = item.description,
                    selected = selectedItem == item.description,
                    onSelect = { setSelectedItem(item.description) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Linha 2
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.drop(3).forEach { item ->
                ItemCard(
                    imageResId = item.imageResId,
                    description = item.description,
                    selected = selectedItem == item.description,
                    onSelect = { setSelectedItem(item.description) }
                )
            }
        }
    }
}

data class ItemData(val imageResId: Int, val description: String)