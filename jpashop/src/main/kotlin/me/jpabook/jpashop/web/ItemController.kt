package me.jpabook.jpashop.web

import me.jpabook.jpashop.domain.item.Book
import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(@Autowired val itemService: ItemService) {

    /**
     * 상품 목록
     */
    @GetMapping(value = ["/items"])
    fun list(model: Model): String {
        val items: MutableList<Item> = itemService.findItems()
        model.addAttribute("items", items)
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

    /**
     * 상품 수정 폼
     */
    @GetMapping(value = ["/items/{itemId}/edit"])
    fun updateItemForm(@PathVariable("itemId") itemId : Long, model: Model): String {
        val item: Book = itemService.findOne(itemId) as Book
        val form = BookForm(
            id = itemId,
            name = item.name,
            price = item.price,
            stockQuantity = item.stockQuantity,
            author = item.author,
            isbn = item.isbn
        )

        model.addAttribute("form", form)
        return "items/updateItemForm"
    }

    /**
     * 상품 수정
     */
    @PostMapping(value = ["/items/{itemId}/edit"])
    fun updateItem(@ModelAttribute("form") form: BookForm): String {
        val book = Book(
            id = form.id,
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
