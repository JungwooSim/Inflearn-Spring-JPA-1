package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,

    var count: Int
)
