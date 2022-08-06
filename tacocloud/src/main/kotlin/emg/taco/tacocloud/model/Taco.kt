package emg.taco.tacocloud.model

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Taco(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @get:NotNull
    @get:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String = "",

    @get:NotNull
    @get:Size(min = 1, message = "You must choose at least one ingredient")
    @ManyToMany var ingredients: MutableList<Ingredient> = mutableListOf()
)