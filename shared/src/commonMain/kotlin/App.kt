@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.muzamil.dictionarykmm.di.initKoin

import data.product.ProductRepository
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.Koin
import ui.theme.ApplyTheme
import ui.theme.component.ProductList
import ui.theme.component.SearchBar
import ui.theme.screen.home.HomeScreen
import ui.theme.screen.home.HomeScreenViewModel

import ui.theme.screen.home.UiState



@Composable
fun App() {
    Napier.base(DebugAntilog())
    ///  val productRepository = koin.get<ProductRepository>()


//    val viewModel = getViewModel(
//        key = "items",
//        factory = viewModelFactory {
//            HomeViewModel(productRepository)
//        }
//    )



    ApplyTheme(useDarkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {

            Navigator(HomeScreen())

        }

    }


}




