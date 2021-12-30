package edu.samgarcia.onepieceapp.domain.use_cases

import edu.samgarcia.onepieceapp.data.repository.Repository
import edu.samgarcia.onepieceapp.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import edu.samgarcia.onepieceapp.domain.use_cases.save_onboarding.SaveOnboardingUseCase

data class UseCases(private val repository: Repository) {
    val saveOnboardingUseCase = SaveOnboardingUseCase(repository)
    val readOnboardingUseCase = ReadOnboardingUseCase(repository)
}