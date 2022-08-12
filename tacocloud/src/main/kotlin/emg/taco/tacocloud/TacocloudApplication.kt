package emg.taco.tacocloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan("emg.taco.*")
@EnableJpaRepositories("emg.taco.*")
@EnableConfigurationProperties
class TacocloudApplication

fun main(args: Array<String>) {
    runApplication<TacocloudApplication>(*args)
}
