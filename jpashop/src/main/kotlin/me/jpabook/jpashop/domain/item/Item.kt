package me.jpabook.jpashop.domain.item

import me.jpabook.jpashop.domain.Category
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open abstract class Item(
    @Id @GeneratedValue
    @Column(name = "item_id")
    val id: Long = 0,

    val name: String,

    val price: Int,

    val stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    val categories: List<Category>
)
