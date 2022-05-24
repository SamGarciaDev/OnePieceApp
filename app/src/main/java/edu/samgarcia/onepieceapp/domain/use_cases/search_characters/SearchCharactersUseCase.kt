package edu.samgarcia.onepieceapp.domain.use_cases.search_characters

import androidx.paging.PagingData
import edu.samgarcia.onepieceapp.data.repository.Repository
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import kotlinx.coroutines.flow.Flow

class SearchCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<OPCharacter>> {
        return repository.searchHeroes(query)
    }
}