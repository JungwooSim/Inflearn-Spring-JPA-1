package me.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@DiscriminatorValue("B")
class Book(
    override var name: String,
    override var price: Int,
    override var stockQuantity: Int) : Item(name = name, price = price, stockQuantity = stockQuantity){
    val author: String? = null
    val isbn: String? = null
}

// name = name, price = price, stockQuantity = stockQuantity, categories = categories
