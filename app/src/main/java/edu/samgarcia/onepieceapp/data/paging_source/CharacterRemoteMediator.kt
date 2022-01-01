package edu.samgarcia.onepieceapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import edu.samgarcia.onepieceapp.data.local.OnePieceDatabase
import edu.samgarcia.onepieceapp.data.remote.OnePieceApi
import edu.samgarcia.onepieceapp.domain.model.CharacterRemoteKeys
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRemoteMediator @Inject constructor(
    private val onepieceApi: OnePieceApi,
    private val onepieceDatabase: OnePieceDatabase
): RemoteMediator<Int, OPCharacter>() {
    private val characterDao = onepieceDatabase.characterDao()
    private val characterRemoteKeysDao = onepieceDatabase.characterRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OPCharacter>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                // First time request, usually
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKeyToPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                // When loading data at the end of PagingData
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage ?: return@load MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }
                // When loading more data at the start of PagingData
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage ?: return@load MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                }
            }

            val response = onepieceApi.getAllCharacters(page = page)

            if (response.characters.isNotEmpty()) {
                onepieceDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        characterRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val keys = response.characters.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            prevPage = response.prevPage,
                            nextPage = response.nextPage
                        )
                    }

                    characterRemoteKeysDao.addAllRemoteKeys(keys)
                    characterDao.addCharacters(characters = response.characters)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch(ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, OPCharacter>): CharacterRemoteKeys? {
        // Gets first page if not empty, gets the first character on that page if not empty
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { character ->
            characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, OPCharacter>
    ): CharacterRemoteKeys? {
        // Gets first page if not empty, gets the first character on that page if not empty
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { character ->
            characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
        }
    }

    private suspend fun getClosestRemoteKeyToPosition(
        state: PagingState<Int, OPCharacter>
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId ->
                characterRemoteKeysDao.getRemoteKeys(characterId = characterId)
            }
        }
    }
}