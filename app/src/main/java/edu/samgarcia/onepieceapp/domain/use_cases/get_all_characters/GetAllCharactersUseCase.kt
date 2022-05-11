package edu.samgarcia.onepieceapp.domain.use_cases.get_all_characters

import androidx.paging.PagingData
import edu.samgarcia.onepieceapp.data.repository.Repository
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<OPCharacter>> {
        return repository.getAllCharacters()
    }
}