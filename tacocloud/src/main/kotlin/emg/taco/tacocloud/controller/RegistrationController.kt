package emg.taco.tacocloud.controller

import emg.taco.tacocloud.data.repository.UserRepository
import emg.taco.tacocloud.model.RegistrationForm
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
class RegistrationController(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    @GetMapping
    fun registerForm(): String = "registration"

    @PostMapping
    fun processRegistration(form: RegistrationForm): String {
        val user = form.toUser(passwordEncoder)
        userRepository.save(user)
        return "redirect:/login"
    }
}