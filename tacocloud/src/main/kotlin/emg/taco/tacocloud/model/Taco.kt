import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Taco(
    @get:NotNull
    @get:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String = "",
    @get:NotNull
    @get:Size(min = 1, message = "You must choose at least one ingredient")
    var ingredients: List<String> = emptyList()
)