package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    val orderPrice: Int,
    val count: Int
)
