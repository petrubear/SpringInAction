package emg.taco.tacocloud.data.repository

import emg.taco.tacocloud.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}