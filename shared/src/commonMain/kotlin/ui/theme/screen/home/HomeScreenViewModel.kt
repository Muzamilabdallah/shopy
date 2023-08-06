package ui.theme.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import data.product.ProductRepository

import domain.product.product.ProductsItem
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: ProductRepository) : ScreenModel {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _category = MutableStateFlow<List<String>>(emptyList())
    val category = _category.asStateFlow()
    var textSearch by mutableStateOf("")
        private set
    private var productList = ArrayList<ProductsItem>()


    init {
        getCategory()
        getProduct()
    }


    private fun getCategory() = coroutineScope.launch {
        try {
            val response = repository.getAllCategory()
            if (response.isNotEmpty()) {
                response.add(0, "All")
                _category.value = response
            }

        } catch (e: Exception) {
            print(e)
        }
    }

    fun getProduct() = coroutineScope.launch {


        try {
            val response = repository.getAllProducts()
            productList = response as ArrayList<ProductsItem>
            _state.update { UiState.Success(response) }

        } catch (e: Exception) {
            print(e)
        }
    }

    fun getProductByCategory(category: String) = coroutineScope.launch {
        try {

            if (category.contains("All"))
                _state.value = UiState.Success(productList)
            else {
                val response = repository.getProductsByCategory(category)
                _state.value = UiState.Success(response)
            }

        } catch (e: Exception) {
            print(e)
        }
    }


    fun updateTextQuery(text: String) {
        textSearch = text
    }

    fun onSearchTextChanged(query: String) {


        if (query.isEmpty())
            _state.update { UiState.Success(productList) }
        else {
            val searchResult = productList.filter { item ->
                item.title.contains(query, true) ||
                        item.category.contains(query, true)
            }

            if (searchResult.isNotEmpty())
                _state.value = UiState.Success(searchResult)
        }
    }


    fun onClearSearch() {
        _state.value=UiState.Success(productList)
        textSearch = ""

    }

}


sealed class UiState {
    data class Success(val item: List<ProductsItem>) : UiState()

    object Loading : UiState()
}
