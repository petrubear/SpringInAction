package emg.taco.tacocloud.service

import emg.taco.tacocloud.data.repository.OrderRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class OrderAdminService(private val orderRepository: OrderRepository) {
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAllOrders() {
        orderRepository.deleteAll()
    }
}