/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*   is unlawful and prohibited.
*/
package com.example.jenkinstesting

import android.media.Rating
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
val brownGreyColor = Color(0xFF959595)
val whiteTwo = Color(0xFFf9f9f9)
val veryLightPink = Color(0xFFeaeaea)

@Composable
fun Subtitle5(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Normal)
)

@Composable
fun Caption(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = brownGreyColor, fontSize = 12.sp, fontWeight = FontWeight.Normal)
)

@ExperimentalFoundationApi
@Composable
fun ProductScreen(productViewModel: ProductViewModel = viewModel()) {
    val sku by productViewModel.sku.observeAsState()
    val title by productViewModel.title.observeAsState()

    LazyColumn(
        content = {
            stickyHeader {
                Toolbar()
            }
            item { ImageHeader() }
            item {
                Text(
                    sku.orEmpty(), modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                    style = TextStyle(color = brownGreyColor),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
            item {
                Text(
                    title.orEmpty(),
                    modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 24.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
            }
            item { RatingRowView() }
            item { PriceView(productViewModel = productViewModel) }
            item { CountView(productViewModel = productViewModel) }
            item { HeaderView(height = 68.dp, title = "Способы получения") }
            item { DeliveryPickupView(productViewModel = productViewModel) }
            item { HeaderView(height = 68.dp, title = "Характеристики") }
            item { CharacteristicsView(productViewModel = productViewModel) }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CharacteristicsView(productViewModel: ProductViewModel) {
    val characteristics by productViewModel.characteristics.observeAsState(mutableListOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        characteristics.map { CharacteristicCell(model = it) }
    }
}

@Composable
fun CharacteristicCell(model: CharacteristicModel) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                model.title,
                modifier = Modifier.weight(0.6f),
                style = TextStyle(color = brownGreyColor)
            )
            Text(
                model.value, modifier = Modifier
                    .weight(0.4f)
                    .padding(8.dp), style = TextStyle(color = Color.Black)
            )
        }

        Divider(color = veryLightPink)
    }
}

@Composable
fun DeliveryPickupView(productViewModel: ProductViewModel) {
    val pickupStoresCount by productViewModel.pickupStoresCount.observeAsState(0)

    Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 22.dp, bottom = 22.dp)) {
        Box(
            Modifier
                .background(color = brownGreyColor)
                .size(24.dp)
        )

        Column(Modifier.padding(start = 24.dp)) {
            Subtitle5("Доставка • Завтра, 25 июля")
            Caption("На складе 112 шт.", Modifier.padding(top = 2.dp))

            if (pickupStoresCount > 0) {
                Subtitle5("Самовывоз • Сегодня", Modifier.padding(top = 16.dp))
                Caption("Доступно в $pickupStoresCount магазинах", Modifier.padding(top = 2.dp))
            }
        }
    }
}

@Composable
fun CountView(productViewModel: ProductViewModel) {
    val availableCount by productViewModel.availableCount.observeAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .padding(start = 16.dp, end = 24.dp)
    ) {
        Box(
            Modifier
                .background(color = brownGreyColor)
                .size(24.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Text("Рассчитать количество", style = TextStyle(color = Color.Black))
            Text(
                "В наличии: $availableCount",
                style = TextStyle(color = brownGreyColor, fontSize = 12.sp)
            )
        }

        Box(
            Modifier
                .background(color = brownGreyColor)
                .size(24.dp)
        )
    }

}

@Composable
fun HeaderView(height: Dp, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = height)
            .background(color = whiteTwo)
            .padding(start = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(title, style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp))
    }
}

@Composable
fun PriceView(productViewModel: ProductViewModel) {
    val itemsInCart by productViewModel.itemsInCart.observeAsState()

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "13 686,00", modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
        Text(
            "₽/шт.", modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp), style = TextStyle(
                color = brownGreyColor, fontSize = 12.sp
            )
        )

        if (itemsInCart == 0) {
            Button(
                onClick = {
                    productViewModel.addToCart()
                },
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .height(48.dp)
                    .width(160.dp)
                    .padding(end = 24.dp),
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        "В корзину",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(48.dp)
                    .width(160.dp)
                    .padding(end = 24.dp)
            ) {
                Text("$itemsInCart")
            }
        }
    }
}

@Composable
fun RatingRowView() {
    Box(
        modifier = Modifier
            .background(color = White)
            .height(52.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ImageHeader() {
    Box(
        modifier = Modifier
            .background(color = White)
            .height(300.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
    ) {
        Text("Back", modifier = Modifier.weight(1f))
        Text("Menu")
    }
}