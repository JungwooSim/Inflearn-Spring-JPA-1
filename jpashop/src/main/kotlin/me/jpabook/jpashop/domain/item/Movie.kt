package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: List<Category>,
    val director: String,
    val actor: String
) : Item(name = name, price = price, stockQuantity = stockQuantity, categories = categories)
