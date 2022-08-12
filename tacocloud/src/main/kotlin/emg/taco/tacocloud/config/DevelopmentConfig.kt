package emg.taco.tacocloud.config

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.data.repository.TacoRepository
import emg.taco.tacocloud.data.repository.UserRepository
import emg.taco.tacocloud.model.Ingredient
import emg.taco.tacocloud.model.Taco
import emg.taco.tacocloud.model.User
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*


private val log = KotlinLogging.logger {}

@Configuration
@Profile("!prod")
class DevelopmentConfig(
    private val ingredientRepository: IngredientRepository,
    private val tacoRepository: TacoRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun dataLoader(): CommandLineRunner {
        return object : CommandLineRunner {
            override fun run(vararg args: String?) {
                log.debug { "########## Sample Data ##########" }
                log.debug { "Setting up ingredients" }
                val flourTortilla = Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)
                val cornTortilla = Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP)
                val groundBeef = Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN)
                val carnitas = Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN)
                val tomatoes = Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES)
                val lettuce = Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES)
                val cheddar = Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
                val jack = Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
                val salsa = Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
                val sourCream = Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)

                ingredientRepository.save(flourTortilla)
                ingredientRepository.save(cornTortilla)
                ingredientRepository.save(groundBeef)
                ingredientRepository.save(carnitas)
                ingredientRepository.save(tomatoes)
                ingredientRepository.save(lettuce)
                ingredientRepository.save(cheddar)
                ingredientRepository.save(jack)
                ingredientRepository.save(salsa)
                ingredientRepository.save(sourCream)

                log.debug { "Ingredients repo count: ${ingredientRepository.count()}" }
                log.debug { "Setting up users" }
                userRepository.save(
                    User(
                        "edison",
                        passwordEncoder.encode("edison"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234"
                    )
                )
                log.debug { "User repo count: ${userRepository.count()}" }
                log.debug { "Setting up tacos" }
                val taco1 = Taco()
                taco1.name = "Carnivore"
                taco1.ingredients = Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar
                )
                tacoRepository.save(taco1)

                val taco2 = Taco()
                taco2.name = "Bovine Bounty"
                taco2.ingredients = Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream
                )
                tacoRepository.save(taco2)

                val taco3 = Taco()
                taco3.name = "Veg-Out"
                taco3.ingredients = Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa
                )
                tacoRepository.save(taco3)
                log.debug { "Taco repo count: ${tacoRepository.count()}" }
                log.debug { "########## Sample Data ##########" }
            }
        }
    }
}