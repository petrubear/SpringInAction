package emg.taco.tacocloud.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "Users")
class User(
    private var username: String? = null,
    private var password: String? = null,
    var fullname: String? = null,
    var street: String? = null,
    var city: String? = null,
    var state: String? = null,
    var zipCode: String? = null,
    var phoneNumber: String? = null
) : UserDetails {
    companion object {
        private val serialVersionUID = 1L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long = 1L

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String? = password

    override fun getUsername(): String? = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}