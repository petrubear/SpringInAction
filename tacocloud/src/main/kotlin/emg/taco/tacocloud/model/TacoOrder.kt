import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class TacoOrder(
    @get:NotBlank(message = "Delivery name is required")
    var deliveryName: String = "",
    @get:NotBlank(message = "Delivery street is required")
    var deliveryStreet: String = "",
    @get:NotBlank(message = "Delivery city is required")
    var deliveryCity: String = "",
    @get:NotBlank(message = "Delivery state is required")
    var deliveryState: String = "",
    @get:NotBlank(message = "Delivery zip is required")
    var deliveryZip: String = "",
    @get:CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String = "",
    @get:Pattern(
        regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
        message = "Must be formatted MM/YY"
    )
    var ccExpiration: String = "",
    @get:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String = "",
    var tacos: List<Taco> = emptyList()
) {
    fun addTaco(taco: Taco) {
        tacos += taco
    }
}