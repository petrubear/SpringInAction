package emg.taco.tacocloud.api

import emg.taco.tacocloud.data.repository.TacoRepository
import emg.taco.tacocloud.model.Taco
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/tacos"], produces = ["application/json", "text/xml"])
@CrossOrigin(origins = ["*"])
//@CrossOrigin(origins = ["http://tacocloud:8443"])
class TacoApiController(private val tacoRepository: TacoRepository) {

    @GetMapping("/recent")
    fun getRecentTacos(): Iterable<Taco> {
        val page = PageRequest.of(0, 12, Sort.by("createdAt").descending())
        return tacoRepository.findAll(page).content
    }

    @GetMapping("/{id}")
    fun getTacoById(@PathVariable id: Long): ResponseEntity<Taco> {
        return tacoRepository.findById(id).map { taco ->
            ResponseEntity.ok(taco)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(code = HttpStatus.CREATED)
    fun postTaco(@RequestBody taco: Taco): Taco {
        return tacoRepository.save(taco)
    }
}