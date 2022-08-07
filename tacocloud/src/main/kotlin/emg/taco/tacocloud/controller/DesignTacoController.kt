package emg.taco.tacocloud.controller

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.model.Ingredient
import emg.taco.tacocloud.model.Taco
import emg.taco.tacocloud.model.TacoOrder
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController(private val ingredientRepository: IngredientRepository) {
    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = ingredientRepository.findAll().toList()

        val types = Ingredient.Type.values()
        types.forEach { type ->
            model.addAttribute(type.toString().lowercase(Locale.getDefault()), filterByType(ingredients, type))
        }
    }

    @ModelAttribute(name = "tacoOrder")
    fun order(): TacoOrder {
        return TacoOrder()
    }

    @ModelAttribute(name = "taco")
    fun taco(): Taco {
        return Taco()
    }

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    private fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): List<Ingredient> {
        return ingredients.filter { it.type == type }
    }


    @PostMapping
    fun processTaco(@Valid taco: Taco, errors: Errors, @ModelAttribute tacoOrder: TacoOrder): String {
        if (errors.hasErrors()) {
            return "design"
        }
        log.info("Processing Taco: $taco")
        tacoOrder.addTaco(taco)
        return "redirect:/orders/current"
    }
}