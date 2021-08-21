package me.jpabook.jpashop.web

import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
}
