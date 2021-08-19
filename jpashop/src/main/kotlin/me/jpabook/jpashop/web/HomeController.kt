package me.jpabook.jpashop.web

import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    val logger = KotlinLogging.logger {}
    @GetMapping("/")
    fun home(): String {
        logger.info("home controller")
        return "home"
    }
}
