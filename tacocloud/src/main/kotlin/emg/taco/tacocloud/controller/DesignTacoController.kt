package emg.taco.tacocloud.controller

import Ingredient
import Taco
import TacoOrder
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController {
    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = listOf<Ingredient>().toMutableList()
        ingredients += Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)
        ingredients += Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP)
        ingredients += Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN)
        ingredients += Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN)
        ingredients += Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES)
        ingredients += Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES)
        ingredients += Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
        ingredients += Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
        ingredients += Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
        ingredients += Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)

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
    fun processTaco(taco: Taco, @ModelAttribute tacoOrder: TacoOrder): String {
        log.info("Processing Taco: $taco")
        tacoOrder.addTaco(taco)
        return "redirect:/orders/current"
    }
}