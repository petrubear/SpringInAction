package emg.taco.tacocloud.api

import emg.taco.tacocloud.data.repository.OrderRepository
import emg.taco.tacocloud.model.TacoOrder
import mu.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

val log = KotlinLogging.logger("OrderApiController")

@RestController
@RequestMapping(path = ["/api/v1/orders"], produces = ["application/json"])
@CrossOrigin(origins = ["http://tacocloud:8080"])
class OrderApiController(private val orderRepository: OrderRepository) {

    @GetMapping(produces = ["application/json"])
    fun allOrders(): Iterable<TacoOrder> {
        return orderRepository.findAll()
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postOrder(@RequestBody order: TacoOrder): TacoOrder {
        return orderRepository.save(order)
    }

    @PutMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun putOrder(
        @PathVariable("orderId") orderId: Long?,
        @RequestBody order: TacoOrder
    ): TacoOrder {
        order.id = orderId
        return orderRepository.save(order)
    }

    @PatchMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun patchOrder(
        @PathVariable("orderId") orderId: Long,
        @RequestBody patch: TacoOrder
    ): TacoOrder {
        val order = orderRepository.findById(orderId).get()
        if (patch.deliveryName != null) {
            order.deliveryName = patch.deliveryName
        }
        if (patch.deliveryStreet != null) {
            order.deliveryStreet = patch.deliveryStreet
        }
        if (patch.deliveryCity != null) {
            order.deliveryCity = patch.deliveryCity
        }
        if (patch.deliveryState != null) {
            order.deliveryState = patch.deliveryState
        }
        if (patch.deliveryZip != null) {
            order.deliveryZip = patch.deliveryZip
        }
        if (patch.ccNumber != null) {
            order.ccNumber = patch.ccNumber
        }
        if (patch.ccExpiration != null) {
            order.ccExpiration = patch.ccExpiration
        }
        if (patch.ccCVV != null) {
            order.ccCVV = patch.ccCVV
        }
        return orderRepository.save(order)
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable("orderId") orderId: Long) {
        try {
            orderRepository.deleteById(orderId)
        } catch (e: EmptyResultDataAccessException) {
            log.error { e }
        }
    }
}