package me.jpabook.jpashop.web

import me.jpabook.jpashop.domain.Member
import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.service.ItemService
import me.jpabook.jpashop.service.MemberService
import me.jpabook.jpashop.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrderController(
    @Autowired private val orderService: OrderService,
    @Autowired private val memberService: MemberService,
    @Autowired private val itemService: ItemService) {

    @GetMapping(value = ["/order"])
    fun createForm(model: Model): String {
        val members: MutableList<Member> = memberService.findMembers()
        val items: MutableList<Item> = itemService.findItems()
        model.addAttribute("members", members)
        model.addAttribute("items", items)
        return "order/orderForm"
    }

    @PostMapping(value = ["/order"])
    fun order(
        @RequestParam("memberId") memberId: Long,
        @RequestParam("itemId") itemId: Long,
        @RequestParam("count") count: Int
    ): String {
        orderService.order(memberId = memberId, itemId = itemId, count = count)
        return "redirect:/orders"
    }
}
