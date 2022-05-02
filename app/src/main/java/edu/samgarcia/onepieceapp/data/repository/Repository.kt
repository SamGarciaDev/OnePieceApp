package edu.samgarcia.onepieceapp.data.repository

import androidx.paging.PagingData
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.repository.DataStoreOperations
import edu.samgarcia.onepieceapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnboardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    fun getAllCharacters(): Flow<PagingData<OPCharacter>> {
        return remote.getAllCharacters()
    }
}