package me.jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    val id: Long = 0,

    val name: String,

    @Embedded
    val address: Address,

    @OneToMany(mappedBy = "member")
    val orders: List<Order>
)


