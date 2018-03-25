package com.wander.notes.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;

import com.wander.notes.data.model.Note;

import java.util.List;

@Database(entities = {Note.class}, version = 1)
public abstract class DBService extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}

@Dao
interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecord(Note note);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> fetchAllData();

    @Query("SELECT * FROM Note WHERE createTimeStamp =:timestamp")
    LiveData<Note> getSingleRecord(String timestamp);

    @Update
    void updateRecord(Note note);

    @Delete
    void deleteRecord(Note note);
}
