package edu.samgarcia.onepieceapp.domain.repository

import androidx.paging.PagingData
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(): Flow<PagingData<OPCharacter>>
    fun searchCharacters(): Flow<PagingData<OPCharacter>>
}