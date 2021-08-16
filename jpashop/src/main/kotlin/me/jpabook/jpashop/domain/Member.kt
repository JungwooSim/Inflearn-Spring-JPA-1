package me.jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long = 0,

    var name: String,

    @Embedded
    var address: Address,

    @OneToMany(mappedBy = "member")
    val orders: ArrayList<Order> = ArrayList()
)


