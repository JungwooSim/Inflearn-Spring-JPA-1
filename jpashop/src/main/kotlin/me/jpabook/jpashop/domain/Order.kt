package me.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order (
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderItems: List<OrderItem>,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    val delivery: Delivery,

    val localDateTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: OrderStatus
)
