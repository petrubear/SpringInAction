package emg.taco.tacocloud.config

import emg.taco.tacocloud.data.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig {
    @Bean
    fun passWordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(userRepository: UserRepository, passwordEncoder: PasswordEncoder): UserDetailsService? {
        return UserDetailsService { username: String ->
            val user = userRepository.findByUsername(username)
            if (user != null) return@UserDetailsService user
            throw UsernameNotFoundException("User '$username' not found")
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeRequests()
            .antMatchers("/design/", "/orders/").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
            .antMatchers("/", "/**").permitAll()

            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design", true)

            .and()
            .logout()
            .logoutSuccessUrl("/login")

            .and()
            .build();
    }
}