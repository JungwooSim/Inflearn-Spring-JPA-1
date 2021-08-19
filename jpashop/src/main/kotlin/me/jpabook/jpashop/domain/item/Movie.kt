package me.jpabook.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    override val name: String,
    override val price: Int,
    override var stockQuantity: Int,
    val director: String,
    val actor: String
) : Item(name = name, price = price, stockQuantity = stockQuantity)
