package emg.taco.tacocloud.model

import org.springframework.security.crypto.password.PasswordEncoder


class RegistrationForm {
    var username: String? = null
    var password: String? = null
    var fullname: String? = null
    var street: String? = null
    var city: String? = null
    var state: String? = null
    var zip: String? = null
    var phone: String? = null

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username, passwordEncoder.encode(password),
            fullname, street, city, state, zip, phone
        )
    }
}