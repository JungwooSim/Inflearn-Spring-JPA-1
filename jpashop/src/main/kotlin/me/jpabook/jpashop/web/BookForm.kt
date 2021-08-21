package me.jpabook.jpashop.web

class BookForm(
    val id: Long? = null,
    val name: String?= null,
    val price: Int?= null,
    val stockQuantity: Int = 0,
    val author: String?= null,
    val isbn: String?= null
)
