package edu.samgarcia.onepieceapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.samgarcia.onepieceapp.data.repository.DataStoreOperationsImpl
import edu.samgarcia.onepieceapp.data.repository.Repository
import edu.samgarcia.onepieceapp.domain.repository.DataStoreOperations
import edu.samgarcia.onepieceapp.domain.use_cases.UseCases
import edu.samgarcia.onepieceapp.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import edu.samgarcia.onepieceapp.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases {
        return UseCases(repository)
    }
}