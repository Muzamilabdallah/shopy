package ui.theme.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.product.product.ProductsItem
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale


import cafe.adriel.voyager.navigator.Navigator

data class ProductDetailScreen(val item: ProductsItem, val navigator: Navigator) : Screen {


    @Composable
    override fun Content() {

        var isVisible by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(rememberScrollState())
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "star",
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier.clickable {
                        navigator.pop()
                    }
                )
                KamelImage(
                    asyncPainterResource(data = item.image),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).aspectRatio(1f)
                )


            }
            verticalSpacer(4.dp)

            ItemPrice(item)

            verticalSpacer(4.dp)

            ItemDesc(item.description)

            verticalSpacer(4.dp)

            ItemSize()

            verticalSpacer(8.dp)

            ItemColors()

            verticalSpacer(8.dp)

            ElevatedButton(
                onClick = { isVisible = true },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("add to cart")
            }
            verticalSpacer(10.dp)
        }


        AnimatedAlert(
            message = "item added to cart",
            isVisible,
            onDismiss = { isVisible = false })

    }

}


@Composable
fun ItemPrice(item: ProductsItem) {
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


}

@Composable
fun ItemDesc(description: String) {
    var isExpanded by remember { mutableStateOf(false) }

    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surface,
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        color = surfaceColor,
        modifier = Modifier.animateContentSize().padding(1.dp)
            .clickable { isExpanded = !isExpanded }
    ) {
        Column() {
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                contentDescription = "fav",
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = description,
                modifier = Modifier.padding(all = 8.dp),
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

val itemSize = listOf("S", "L", "XL", "XXL")
val itemColors = listOf(Color.Black, Color.Blue, Color.Gray, Color.DarkGray)


@Composable
fun ItemSize() {

    LazyRow(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(itemSize) {
            Box(

                modifier = Modifier.padding(8.dp).width(40.dp).height(40.dp)
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = CircleShape)
            ) {

                Text(
                    it,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
        }
    }
}

@Composable
fun ItemColors() {

    LazyRow(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(itemColors) {
            Box(

                modifier = Modifier.padding(8.dp).width(20.dp).height(20.dp)
                    .background(color = it, shape = CircleShape)
            )
        }
    }
}


@Composable
fun AnimatedAlert(
    message: String,
    isVisible: Boolean,
    onDismiss: () -> Unit
) {
    var alerterVisibility by remember { mutableStateOf(isVisible) }

    val transition = updateTransition(targetState = alerterVisibility, label = "")

    val alerterScale by transition.animateFloat(
        transitionSpec = {
            keyframes {
                durationMillis = 1000
                0f at 0 // Initial scale (zoomed in)
                1.5f at 500 // Mid-scale (zoomed out)
                1f at 1000 // Final scale (normal)
            }
        }
    ) { state ->
        if (state) 1f else 0f
    }

    LaunchedEffect(isVisible) {
        alerterVisibility = isVisible
        // Wait for the animation to finish before dismissing
        delay(1000)
        onDismiss()

    }

    if (alerterVisibility) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(2f).wrapContentHeight(Alignment.Top)
                .padding(16.dp)
                .scale(alerterScale),
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.primary,
            contentColor = contentColorFor(MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = message,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }


}

