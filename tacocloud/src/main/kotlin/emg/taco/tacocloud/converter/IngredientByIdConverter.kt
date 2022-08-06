package emg.taco.tacocloud.converter

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.model.Ingredient
import org.springframework.core.convert.converter.Converter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component


@Component
class IngredieOntByIdConverter(private val ingredientRepository: IngredientRepository) :
    Converter<String, Ingredient?> {

    override fun convert(id: String): Ingredient? {
        return ingredientRepository.findByIdOrNull(id)
    }
}