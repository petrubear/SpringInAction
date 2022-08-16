package emg.taco.tacocloud.client

import emg.taco.tacocloud.model.Ingredient
import mu.KotlinLogging
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


private val log = KotlinLogging.logger {}

@Service
class TacoCloudClient(
    private val rest: RestTemplate
) {

    fun getIngredientById(ingredientId: String): Ingredient? {
        return rest.getForObject(
            "http://localhost:8080/ingredients/{id}",
            Ingredient::class.java, ingredientId
        )
    }

    fun getIngredientByIdWithMap(ingredientId: String): Ingredient? {
        val urlVariables = HashMap<String, String>()
        urlVariables["id"] = ingredientId
        return rest.getForObject(
            "http://localhost:8080/ingredients/{id}",
            Ingredient::class.java, urlVariables
        )
    }

    fun getIngredientByIdWithURI(ingredientId: String): Ingredient? {
        val urlVariables = HashMap<String, String>()
        urlVariables["id"] = ingredientId
        val url: URI = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8080/ingredients/{id}")
            .build(urlVariables)
        return rest.getForObject(url, Ingredient::class.java)
    }

    fun getIngredientByIdWithEntity(ingredientId: String?): Ingredient? {
        val responseEntity = rest.getForEntity(
            "http://localhost:8080/ingredients/{id}",
            Ingredient::class.java, ingredientId
        )
        log.info(
            "Fetched time: {}",
            responseEntity.headers.date
        )
        return responseEntity.body
    }


    fun getAllIngredients(): List<Ingredient>? {
        return rest.exchange<List<Ingredient>>("http://localhost:8080/ingredients",
            HttpMethod.GET, null, object : ParameterizedTypeReference<List<Ingredient>>() {})
            .getBody()
    }

    fun updateIngredient(ingredient: Ingredient) {
        rest.put(
            "http://localhost:8080/ingredients/{id}",
            ingredient, ingredient.id
        )
    }

    fun createIngredient(ingredient: Ingredient?): Ingredient? {
        return rest.postForObject(
            "http://localhost:8080/ingredients",
            ingredient, Ingredient::class.java
        )
    }

    fun deleteIngredient(ingredient: Ingredient) {
        rest.delete(
            "http://localhost:8080/ingredients/{id}",
            ingredient.id
        )
    }
}