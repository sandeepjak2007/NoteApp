package com.sandeep.todoapp.data.di

import android.app.Application
import androidx.room.Room
import com.sandeep.todoapp.data.NoteDao
import com.sandeep.todoapp.data.NoteDatabase
import com.sandeep.todoapp.data.repo.NoteRepository
import com.sandeep.todoapp.data.repo.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun providesNoteDao(db: NoteDatabase): NoteDao {
        return db.getNoteDao()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(dao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(dao)
    }


}