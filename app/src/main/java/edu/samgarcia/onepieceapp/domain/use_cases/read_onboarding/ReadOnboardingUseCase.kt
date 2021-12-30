package edu.samgarcia.onepieceapp.domain.use_cases.read_onboarding

import edu.samgarcia.onepieceapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnboardingState()
    }
}