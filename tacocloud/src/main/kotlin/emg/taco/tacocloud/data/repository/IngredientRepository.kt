package emg.taco.tacocloud.data.repository

import emg.taco.tacocloud.model.Ingredient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : CrudRepository<Ingredient, String> {
}