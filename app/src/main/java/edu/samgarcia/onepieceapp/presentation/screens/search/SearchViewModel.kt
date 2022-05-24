package edu.samgarcia.onepieceapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedCharacters = MutableStateFlow<PagingData<OPCharacter>>(PagingData.empty())
    val searchedCharacters = _searchedCharacters

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchCharacters(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchCharactersUseCase(query).cachedIn(viewModelScope).collect {
                _searchedCharacters.value = it
            }
        }
    }
}