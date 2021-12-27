package edu.samgarcia.onepieceapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.samgarcia.onepieceapp.data.local.OnePieceDatabase
import edu.samgarcia.onepieceapp.utils.Constants.ONE_PIECE_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OnePieceDatabase {
        return Room.databaseBuilder(
            context,
            OnePieceDatabase::class.java,
            ONE_PIECE_DATABASE_NAME
        ).build()
    }
}