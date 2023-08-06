package ui.theme.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.product.product.ProductsItem
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.theme.screen.home.ProductDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductList(item: List<ProductsItem> ) {


    val navigator = LocalNavigator.currentOrThrow

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(item, key = { it.id }) { item ->
            itemCard(item, onItemClicked = {
              navigator.push(ProductDetailScreen(item,navigator))
            })
        }
    }

}



@Composable
fun itemCard(item: ProductsItem, onItemClicked: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier = Modifier.padding(6.dp)
            .fillMaxWidth().aspectRatio(0.7f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )

    ) {


        Column(
            verticalArrangement = Arrangement.Top,

            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Box(modifier = Modifier.clickable { onItemClicked.invoke() }) {
                KamelImage(
                    asyncPainterResource(data = item.image),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).aspectRatio(1.2f)
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "fav",
                    tint = MaterialTheme.colorScheme.surfaceTint
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "${item.price}$",
                    modifier = Modifier.wrapContentHeight().padding(all = 4.dp)
                )

                Row(modifier = Modifier.wrapContentHeight().padding(all = 4.dp)) {
                    Text(
                        "${item.rating?.rate} ",


                        )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "star",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }


            }

            Text(
                "${item.title}$",
                modifier = Modifier.wrapContentHeight().padding(vertical = 8.dp, horizontal = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal
                )
            )
        }


    }
}

