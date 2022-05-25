package edu.samgarcia.onepieceapp.data.repository

import edu.samgarcia.onepieceapp.data.local.OnePieceDatabase
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(onePieceDatabase: OnePieceDatabase): LocalDataSource {
    private val characterDao = onePieceDatabase.characterDao()

    override suspend fun getSelectedCharacter(id: Int): OPCharacter {
        return characterDao.getSelectedCharacter(id)
    }
}