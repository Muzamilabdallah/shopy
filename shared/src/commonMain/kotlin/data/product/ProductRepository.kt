package data.product


import domain.product.product.ProductsItem

interface ProductRepository {
    suspend fun getAllCategory():ArrayList<String>
    suspend fun getAllProducts():List<ProductsItem>
    suspend fun getProductsByCategory(category: String):List<ProductsItem>

}