package emg.taco.tacocloud.api

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.model.Ingredient
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/ingredients"], produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class IngredientApiController(private val ingredientRepository: IngredientRepository) {

    @GetMapping("/all")
    fun getAllIngredients(): Iterable<Ingredient> {
        return ingredientRepository.findAll()
    }
}