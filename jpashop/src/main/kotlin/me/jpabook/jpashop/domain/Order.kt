package me.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(member: Member, orderItems: MutableList<OrderItem>, delivery: Delivery, localDateTime: LocalDateTime, status: OrderStatus) {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = orderItems

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery = delivery

    var localDateTime: LocalDateTime = localDateTime

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = status

    //==연관관계 메서드==//
    fun changeMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    //==연관관계 메서드==//
    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun changeDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    // 생성 메서드
    fun createOrder(member: Member, delivery: Delivery, orderItems: MutableList<OrderItem>) {
        val order = Order(
            member = member,
            delivery = delivery,
            orderItems = orderItems,
            status = OrderStatus.ORDER,
            localDateTime = LocalDateTime.now()
        )
    }

    // 주문 취소
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL

        orderItems.forEach {
            it.cancel()
        }
    }

    // 전체 주문 가격 조회
    fun getTotalPrice(): Int {
        var totalPrice: Int = 0

        this.orderItems.forEach {
            totalPrice += it.getTotalPrice()
        }

        return totalPrice
    }
}
