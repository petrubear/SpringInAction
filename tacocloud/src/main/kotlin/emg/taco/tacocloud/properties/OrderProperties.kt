package emg.taco.tacocloud.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Component
@ConfigurationProperties("taco.order")
class OrderProperties(
    @Min(value = 5, message = "must be between 5 and 25")
    @Max(value = 25, message = "must be between 5 and 25")
    var pageSize: Int = 20
)
