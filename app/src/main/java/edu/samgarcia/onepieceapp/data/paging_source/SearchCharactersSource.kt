package edu.samgarcia.onepieceapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.samgarcia.onepieceapp.data.remote.OnePieceApi
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import javax.inject.Inject

class SearchCharactersSource @Inject constructor(
    private val onePieceApi: OnePieceApi,
    private val query: String
): PagingSource<Int, OPCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OPCharacter> {
        return try {
            val apiResponse = onePieceApi.searchCharacters(name = query)
            val characters = apiResponse.characters

            if (characters.isNotEmpty()) {
                LoadResult.Page(
                    data = characters,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OPCharacter>): Int? {
        return state.anchorPosition
    }
}