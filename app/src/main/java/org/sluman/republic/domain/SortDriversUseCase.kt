package org.sluman.republic.domain

class SortDriversUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): List<DriverDomainEntity>? {
        repository.sortedAsc = !repository.sortedAsc
        return repository.getAllDrivers()?.let { drivers ->
            if (repository.sortedAsc) {
                drivers.sortedWith(compareBy { it.name.split(" ")[1] })
            } else {
                drivers.sortedWith(compareBy { it.name.split(" ")[1] }).asReversed()
            }
        }
    }

}