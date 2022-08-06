package emg.taco.tacocloud.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Ingredient(
    @Id
    var id: String? = null,
    var name: String,
    var type: Type
) {
    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}