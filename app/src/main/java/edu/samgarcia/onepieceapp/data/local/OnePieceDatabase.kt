package edu.samgarcia.onepieceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.samgarcia.onepieceapp.data.local.dao.CharacterDao
import edu.samgarcia.onepieceapp.data.local.dao.CharacterRemoteKeyDao
import edu.samgarcia.onepieceapp.domain.model.Character
import edu.samgarcia.onepieceapp.domain.model.CharacterRemoteKey

@Database(entities = [Character::class, CharacterRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeyDao(): CharacterRemoteKeyDao
}