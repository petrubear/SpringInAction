package emg.taco.tacocloud.controller

import emg.taco.tacocloud.data.repository.OrderRepository
import emg.taco.tacocloud.model.TacoOrder
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController(val orderRepository: OrderRepository) {
    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid tacoOrder: TacoOrder, errors: Errors, sessionStatus: SessionStatus): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info { "Processing order: $tacoOrder" }
        orderRepository.save(tacoOrder)
        sessionStatus.setComplete()
        return "redirect:/"
    }
}