data class TacoOrder(
    var deliveryName: String = "",
    var deliveryStreet: String = "",
    var deliveryCity: String = "",
    var deliveryState: String = "",
    var deliveryZip: String = "",
    var ccNumber: String = "",
    var ccExpiration: String = "",
    var ccCVV: String = "",
    var tacos: List<Taco> = emptyList()
) {
    fun addTaco(taco: Taco) {
        tacos += taco
    }
}