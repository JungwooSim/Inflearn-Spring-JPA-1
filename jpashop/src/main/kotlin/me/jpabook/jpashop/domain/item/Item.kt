package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import me.jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open abstract class Item(
    @Id @GeneratedValue
    @Column(name = "item_id")
    val id: Long? = null,

    val name: String,

    val price: Int,

    var stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    val categories: List<Category>) {

    //==비즈니스 로직==//
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock: Int = this.stockQuantity - quantity

        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }

        this.stockQuantity = restStock
    }
}
