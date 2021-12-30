package edu.samgarcia.onepieceapp.domain.use_cases.save_onboarding

import edu.samgarcia.onepieceapp.data.repository.Repository

class SaveOnboardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnboardingState(completed)
    }
}