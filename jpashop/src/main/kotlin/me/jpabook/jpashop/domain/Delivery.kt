package me.jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Delivery(
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long,

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    val order: Order,

    @Embedded
    val address: Address,


    val status: DeliveryStatus
)
