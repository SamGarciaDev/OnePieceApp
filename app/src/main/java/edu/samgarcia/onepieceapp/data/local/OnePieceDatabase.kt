package edu.samgarcia.onepieceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.samgarcia.onepieceapp.data.local.dao.CharacterDao
import edu.samgarcia.onepieceapp.data.local.dao.CharacterRemoteKeysDao
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.domain.model.CharacterRemoteKeys

@Database(entities = [OPCharacter::class, CharacterRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}