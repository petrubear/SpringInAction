package emg.taco.tacocloud

import emg.taco.tacocloud.data.repository.IngredientRepository
import emg.taco.tacocloud.model.Ingredient
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

private val log = KotlinLogging.logger {}

@SpringBootApplication
@EntityScan("emg.taco.*")
@EnableJpaRepositories("emg.taco.*")
@EnableConfigurationProperties
class TacocloudApplication(val repo: IngredientRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        log.debug { "Setting up ingredients" }
        repo.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
        repo.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
        repo.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
        repo.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
        repo.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
        repo.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
        repo.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
        repo.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
        repo.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
        repo.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
        log.debug { "Ingredientes repo count: ${repo.count()}" }
    }

}

fun main(args: Array<String>) {
    runApplication<TacocloudApplication>(*args)
}
