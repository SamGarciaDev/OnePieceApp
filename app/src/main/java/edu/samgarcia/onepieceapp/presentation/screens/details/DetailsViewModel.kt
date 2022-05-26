package edu.samgarcia.onepieceapp.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.use_cases.UseCases
import edu.samgarcia.onepieceapp.utils.Constants.DETAILS_ARGUMENT_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _selectedCharacter: MutableStateFlow<OPCharacter?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<OPCharacter?> = _selectedCharacter

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val characterId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedCharacter.value = characterId?.let { id ->
                useCases.getSelectedCharacterUseCase(id)
            }
        }
    }
}