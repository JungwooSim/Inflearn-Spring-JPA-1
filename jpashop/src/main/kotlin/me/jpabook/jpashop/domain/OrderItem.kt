package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,

    var orderPrice: Int,

    var count: Int) {

    fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
        val orderItem = OrderItem(item = item, orderPrice = orderPrice, count = count)

        item.removeStock(count)

        return orderItem
    }

    // 주문 취소
    fun cancel() {
        this.item.addStock(count)
    }

    // 주문상품 전체 가격 조회
    fun getTotalPrice(): Int {
        return orderPrice * count
    }
}
