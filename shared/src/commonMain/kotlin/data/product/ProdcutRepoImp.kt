package data.product

import core.Constant

import domain.product.product.ProductsItem
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductRepoImp(private val httpClient: HttpClient) : ProductRepository {

    override suspend fun getAllCategory(): ArrayList<String> {
        return try {
            val response =
                httpClient.get(Constant.PRODUCTS + "/categories").body<ArrayList<String>>()
            Napier.d("categoryResponse $response")
            return response
        } catch (ex: Exception) {
            Napier.d("categoryResponse $ex")
            return emptyList<String>() as ArrayList<String>
        }
    }

    override suspend fun getAllProducts(): List<ProductsItem> {
        return try {
            val response = httpClient.get(Constant.PRODUCTS).body<List<ProductsItem>>()
            Napier.d("productItem $response")
            return response
        } catch (ex: Exception) {

            Napier.d("productItem $ex")
            listOf(
                ProductsItem(
                    title = "tshirt",
                    category = "dd",
                    description = "descitption",
                    id = 1,
                    image = "",
                    price = 2.0,

                    )
            )
        }

    }

    override suspend fun getProductsByCategory(category: String): List<ProductsItem> {
        return try {
            val response =
                httpClient.get(Constant.PRODUCTS + "/category/${category}")
                    .body<List<ProductsItem>>()
            Napier.d("productResponse $response")
            return response
        } catch (ex: Exception) {
            Napier.d("productResponse $ex")
            return emptyList()
        }
    }


}