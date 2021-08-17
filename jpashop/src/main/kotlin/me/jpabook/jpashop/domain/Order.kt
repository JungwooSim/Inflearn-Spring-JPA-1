package me.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(member: Member, orderItems: ArrayList<OrderItem>, delivery: Delivery, localDateTime: LocalDateTime, status: OrderStatus) {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long = 0

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
}
