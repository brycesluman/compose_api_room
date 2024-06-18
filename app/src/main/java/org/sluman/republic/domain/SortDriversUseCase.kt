package org.sluman.republic.domain

class SortDriversUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): List<DriverDomainEntity>? {
        return repository.getSortedDrivers()
    }

}