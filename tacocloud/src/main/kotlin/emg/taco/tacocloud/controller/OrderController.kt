package emg.taco.tacocloud.controller

import TacoOrder
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {
    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(tacoOrder: TacoOrder, sessionStatus: SessionStatus): String {
        log.info { "Processing order: $tacoOrder" }
        sessionStatus.setComplete()
        return "redirect:/"
    }
}