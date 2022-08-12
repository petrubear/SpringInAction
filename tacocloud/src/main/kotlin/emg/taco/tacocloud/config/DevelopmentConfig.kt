package emg.taco.tacocloud.config

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.data.repository.UserRepository
import emg.taco.tacocloud.model.Ingredient
import emg.taco.tacocloud.model.User
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder

private val log = KotlinLogging.logger {}

@Configuration
@Profile("!prod")
class DevelopmentConfig(
    val ingredientRepository: IngredientRepository,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun dataLoader(): CommandLineRunner {
        return object : CommandLineRunner {
            override fun run(vararg args: String?) {
                log.debug { "Setting up ingredients" }
                ingredientRepository.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
                ingredientRepository.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
                ingredientRepository.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
                ingredientRepository.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
                ingredientRepository.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
                ingredientRepository.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
                ingredientRepository.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
                ingredientRepository.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
                ingredientRepository.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
                ingredientRepository.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
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
            }
        }
    }
}