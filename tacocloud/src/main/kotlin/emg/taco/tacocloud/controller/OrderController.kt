package emg.taco.tacocloud.controller

import emg.taco.tacocloud.data.repository.OrderRepository
import emg.taco.tacocloud.data.repository.UserRepository
import emg.taco.tacocloud.model.TacoOrder
import emg.taco.tacocloud.model.User
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import java.security.Principal
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) {
    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid tacoOrder: TacoOrder,
        errors: Errors,
        sessionStatus: SessionStatus,
        authentication: Authentication?,
    ): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info { "Processing order: $tacoOrder" }
        tacoOrder.user = authentication?.principal as User

        orderRepository.save(tacoOrder)
        sessionStatus.setComplete()
        return "redirect:/"
    }
}