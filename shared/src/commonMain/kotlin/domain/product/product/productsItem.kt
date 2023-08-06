package domain.product.product

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class ProductsItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    @Contextual
    val rating: Rating?=null,
    val title: String
)