package me.jpabook.jpashop.web

import me.jpabook.jpashop.domain.item.Book
import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(@Autowired val itemService: ItemService) {

    /**
     * 상품 목록
     */
    @GetMapping(value = ["/items"])
    fun list(model: Model): String {
        val items: MutableList<Item> = itemService.findItems()
        model.addAttribute("itmes", items)
        return "items/itemList"
    }

    /**
     * 상품 등록 폼
     */
    @GetMapping(value = ["/items/new"])
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    /**
     * 상품 등록
     */
    @PostMapping(value = ["/items/new"])
    fun create(form: BookForm): String {
        val book = Book(
            name = form.name,
            price = form.price,
            stockQuantity = form.stockQuantity,
            author = form.author,
            isbn = form.isbn
        )

        itemService.saveItem(book)

        return "redirect:/items"
    }
}
