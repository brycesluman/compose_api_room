package org.sluman.republic.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sluman.republic.data.MainRepositoryImpl
import org.sluman.republic.data.RouteRepositoryImpl
import org.sluman.republic.data.network.ApiClient
import org.sluman.republic.database.DriverDao
import org.sluman.republic.database.RepublicDatabase
import org.sluman.republic.database.RouteDao
import org.sluman.republic.domain.FetchDriversAndRoutesUseCase
import org.sluman.republic.domain.GetRouteUseCase
import org.sluman.republic.domain.MainRepository
import org.sluman.republic.domain.RouteRepository
import org.sluman.republic.domain.SortDriversUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RepublicDatabase {
        return Room.databaseBuilder(
            context,
            RepublicDatabase::class.java,
            RepublicDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRouteDao(database: RepublicDatabase): RouteDao {
        return database.routeDao()
    }

    @Singleton
    @Provides
    fun provideDriverDao(database: RepublicDatabase): DriverDao {
        return database.driverDao()
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun provideDriverRepository(
        apiClient: ApiClient,
        driverDao: DriverDao,
        routeDao: RouteDao
    ): MainRepository {
        return MainRepositoryImpl(apiClient, driverDao, routeDao)
    }

    @Provides
    @Singleton
    fun provideRouteRepository(routeDao: RouteDao): RouteRepository {
        return RouteRepositoryImpl(routeDao)
    }

    @Provides
    @Singleton
    fun provideFetchDriverAndRoutesUseCase(mainRepository: MainRepository): FetchDriversAndRoutesUseCase {
        return FetchDriversAndRoutesUseCase(mainRepository)
    }

    @Provides
    @Singleton
    fun provideGetRouteUseCase(routeRepository: RouteRepository): GetRouteUseCase {
        return GetRouteUseCase(routeRepository)
    }

    @Provides
    @Singleton
    fun provideSortDriversUseCase(mainRepository: MainRepository): SortDriversUseCase {
        return SortDriversUseCase(mainRepository)
    }
}