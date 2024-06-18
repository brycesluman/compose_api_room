package org.sluman.republic.domain

class FetchDriversAndRoutesUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): List<DriverDomainEntity>? {
        return repository.fetchDriversAndRoutesReturnDrivers()
    }
}