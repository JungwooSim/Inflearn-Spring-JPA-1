package me.jpabook.jpashop.web

import me.jpabook.jpashop.domain.Address
import me.jpabook.jpashop.domain.Member
import me.jpabook.jpashop.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class MemberController(
    @Autowired
    val memberService: MemberService) {

    @GetMapping("/members/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(@Valid form: MemberForm, result: BindingResult): String {

        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        val address = Address(
            city = form.city,
            street = form.street,
            zipcode = form.zipcode
        )
        val member = Member(name = form.name!!, address = address)
        memberService.join(member)

        return "redirect:/"
    }
}
