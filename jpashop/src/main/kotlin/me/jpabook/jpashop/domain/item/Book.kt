package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: List<Category>,
    val author: String,
    val isbn: String
) : Item(name = name, price = price, stockQuantity = stockQuantity, categories = categories)
