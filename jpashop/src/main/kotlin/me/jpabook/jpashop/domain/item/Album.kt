package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: List<Category>,
    val artist: String,
    val etc: String
) : Item(name = name, price = price, stockQuantity = stockQuantity, categories = categories)
