package emg.taco.tacocloud.data.repository

import emg.taco.tacocloud.model.TacoOrder
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<TacoOrder, Long> {
    fun findByDeliveryZip(deliveryZip: String): List<TacoOrder>
    fun readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip: String, startDate: Date, endDate: Date): List<TacoOrder>
    fun findByDeliveryNameAndDeliveryCityAllIgnoreCase(deliveryName: String, deliveryCity: String)
    fun findByDeliveryCityOrderByDeliveryCity(deliveryCity: String): List<TacoOrder>
}