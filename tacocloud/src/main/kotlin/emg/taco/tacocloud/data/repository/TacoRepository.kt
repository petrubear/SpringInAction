package emg.taco.tacocloud.data.repository

import emg.taco.tacocloud.model.Taco
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TacoRepository : PagingAndSortingRepository<Taco, Long> {
}