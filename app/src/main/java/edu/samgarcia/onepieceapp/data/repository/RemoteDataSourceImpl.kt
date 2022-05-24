package edu.samgarcia.onepieceapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import edu.samgarcia.onepieceapp.data.local.OnePieceDatabase
import edu.samgarcia.onepieceapp.data.paging_source.CharacterRemoteMediator
import edu.samgarcia.onepieceapp.data.paging_source.SearchCharactersSource
import edu.samgarcia.onepieceapp.data.remote.OnePieceApi
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.repository.RemoteDataSource
import edu.samgarcia.onepieceapp.utils.Constants.CHARACTERS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val onePieceApi: OnePieceApi,
    private val onePieceDatabase: OnePieceDatabase
): RemoteDataSource {
    private val characterDao = onePieceDatabase.characterDao()

    override fun getAllCharacters(): Flow<PagingData<OPCharacter>> {
        val pagingSourceFactory = { characterDao.getAllCharacters() }

        return Pager(
            config = PagingConfig(pageSize = CHARACTERS_PER_PAGE),
            remoteMediator = CharacterRemoteMediator(
                onePieceApi = onePieceApi,
                onePieceDatabase = onePieceDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchCharacters(query: String): Flow<PagingData<OPCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = CHARACTERS_PER_PAGE),
            pagingSourceFactory = {
                SearchCharactersSource(onePieceApi = onePieceApi, query = query)
            }
        ).flow
    }
}