package emg.taco.tacocloud.converter

import Ingredient
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


@Component
class IngredieOntByIdConverter : Converter<String, Ingredient?> {
    private val ingredientMap: MutableMap<String, Ingredient> = HashMap()

    init {
        ingredientMap["FLTO"] = Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)
        ingredientMap["COTO"] = Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP)
        ingredientMap["GRBF"] = Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN)
        ingredientMap["CARN"] = Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN)
        ingredientMap["TMTO"] = Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES)
        ingredientMap["LETC"] = Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES)
        ingredientMap["CHED"] = Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
        ingredientMap["JACK"] = Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
        ingredientMap["SLSA"] = Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
        ingredientMap["SRCR"] = Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
    }

    override fun convert(id: String): Ingredient? {
        return ingredientMap[id]
    }
}