package me.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    override var id: Long? = null,
    override var name: String? = null,
    override var price: Int? = null,
    override var stockQuantity: Int,
    var author: String? = null,
    var isbn: String? = null
) : Item(name = name, price = price, stockQuantity = stockQuantity)

// name = name, price = price, stockQuantity = stockQuantity, categories = categories
