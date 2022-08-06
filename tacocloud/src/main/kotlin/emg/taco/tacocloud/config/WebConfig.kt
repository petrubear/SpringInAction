package emg.taco.tacocloud.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        // Esto reemplaza a la clase HomeController
        registry.addViewController("/").setViewName("home")
    }
}