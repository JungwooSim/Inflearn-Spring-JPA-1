package me.jpabook.jpashop.domain

import me.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long = 0,

    var name: String,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "cateogry_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: ArrayList<Item> = arrayListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category,

    @OneToMany(mappedBy = "parent")
    var child: ArrayList<Category> = arrayListOf()
) {
    fun addChildCategory(child: Category) {
        this.child.add(child)
        child.parent = child
    }
}
