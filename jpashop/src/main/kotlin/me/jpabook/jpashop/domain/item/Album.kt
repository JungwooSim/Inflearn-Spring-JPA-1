package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    override val name: String,
    override val price: Int,
    override var stockQuantity: Int,
    val artist: String,
    val etc: String
) : Item(name = name, price = price, stockQuantity = stockQuantity)
