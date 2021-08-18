package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(id: Long? = null, item: Item, orderPrice: Int, count: Int) {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item = item

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null

    val orderPrice: Int = orderPrice

    val count: Int = count

    fun createOrderItem() {
        item.removeStock(count)
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
