package edu.samgarcia.onepieceapp.domain.use_cases.get_seelected_character

import edu.samgarcia.onepieceapp.data.repository.Repository
import edu.samgarcia.onepieceapp.domain.model.OPCharacter

class GetSelectedCharacterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): OPCharacter {
        return repository.getSelectedCharacter(id)
    }
}