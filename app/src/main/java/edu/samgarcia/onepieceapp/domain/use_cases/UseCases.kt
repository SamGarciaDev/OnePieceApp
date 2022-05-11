package edu.samgarcia.onepieceapp.domain.use_cases

import edu.samgarcia.onepieceapp.domain.use_cases.get_all_characters.GetAllCharactersUseCase
import edu.samgarcia.onepieceapp.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import edu.samgarcia.onepieceapp.domain.use_cases.save_onboarding.SaveOnboardingUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val getAllCharactersUseCase: GetAllCharactersUseCase
)