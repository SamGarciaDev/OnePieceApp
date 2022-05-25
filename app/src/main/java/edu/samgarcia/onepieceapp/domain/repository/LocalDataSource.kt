package edu.samgarcia.onepieceapp.domain.repository

import edu.samgarcia.onepieceapp.domain.model.OPCharacter

interface LocalDataSource {
    suspend fun getSelectedCharacter(id: Int): OPCharacter
}