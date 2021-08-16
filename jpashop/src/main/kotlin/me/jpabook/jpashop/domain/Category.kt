package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    val id: Long = 0,

    val name: String,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "cateogry_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: List<Item>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Category,

    @OneToMany(mappedBy = "parent")
    val child: List<Category>
)
