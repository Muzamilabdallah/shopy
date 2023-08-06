import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoryUi(category: List<String>, selectedItem: (String,Int) -> Unit) {
    var selected by remember { mutableStateOf(0) }
    val onItemSelected: (String) -> Unit = { item ->
        selected = category.indexOf(item)
            selectedItem.invoke(item,selected)
    }

    LazyRow(contentPadding = PaddingValues(8.dp)) {
        itemsIndexed(category) { index, item ->
            CategoryItem(item, selected == index, onItemSelected)
        }
    }


}


@Composable
fun CategoryItem(item: String, isSelected: Boolean, onItemSelected: (String) -> Unit) {
    Box(
        modifier = Modifier.wrapContentHeight().widthIn(100.dp).padding(4.dp)
            .clickable { onItemSelected.invoke(item) }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.background,
                CircleShape
            )

    ) {
        Text (
            text = item,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp).align(Alignment.Center),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Normal
            ),
            color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurface,

            )


    }
}




