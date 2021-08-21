package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import me.jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    @Id @GeneratedValue
    @Column(name = "item_id")
    open val id: Long? = null,

    open val name: String? = null,

    open val price: Int? = null,

    open var stockQuantity: Int = 0,

    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf()) {

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
