package ui.theme.screen.home;

import CategoryUi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

import di.getScreenModel
import ui.theme.component.ProductList
import ui.theme.component.SearchBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeScreenViewModel = getScreenModel()
        val category = viewModel.category.collectAsState().value
        val state = viewModel.state.collectAsState().value
        Scaffold(topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu icon")
                }


            }, title = { Text("ShopY") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        3.dp
                    )
                )
            )
        }, content = {

            Column(

                modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding())
            ) {

                SearchBar(
                    searchText = viewModel.textSearch,
                    placeholderText = "search products",
                    onSearchTextChanged = {
                        viewModel.updateTextQuery(it)
                        viewModel.onSearchTextChanged(viewModel.textSearch)

                    },
                    onClearClick = {
                        viewModel.onClearSearch()
                    },

                    )
                verticalSpacer(10.dp)

                CategoryUi(category) { category, index ->

                        viewModel.getProductByCategory(category)

                }

                verticalSpacer(10.dp)

                when (state) {
                    is UiState.Loading -> LoadingUi()


                    is UiState.Success -> ProductList(
                        item = state.item,
                    )


                }

            }
        })
    }
}


@Composable
fun verticalSpacer(dp: Dp) {

    Spacer(modifier = Modifier.height(height = dp))
}

@Composable
fun LoadingUi() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 28.dp).align(Alignment.CenterHorizontally),
            color = Color.Red
        )

    }
}