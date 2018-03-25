package com.wander.notes.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask

import com.wander.notes.data.model.Note

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Singleton
class NoteRepository @Inject
constructor(private val restService: RESTService, private val dbService: DBService) {
    internal var data: MutableLiveData<List<Note>>? = null

    fun getNoteList(userId: String): LiveData<List<Note>> {
        if (data == null) data = MutableLiveData()

        val noteList = ArrayList<Note>()
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "1521890236113", "1"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "1521890236113", "1"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "1521890236113", "1"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "1521890236113", "1"))

                restService.getNoteList(userId).enqueue(object : Callback<List<Note>> {
                    override fun onResponse(call: Call<List<Note>>?, response: Response<List<Note>>?) {
                        for (note in response!!.body()!!) {
                            insertInDatabaseAsyncTask(note).execute()
                        }
                    }

                    override fun onFailure(call: Call<List<Note>>?, t: Throwable?) {

                    }
                })
        return dbService.daoAccess().fetchAllData()
    }

    fun updateNote(note: Note) {
        insertInDatabaseAsyncTask(note).execute()
        //        dbService.daoAccess().insertRecord(note);
    }

    internal inner class insertInDatabaseAsyncTask(var note: Note) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void?): Int {
            dbService.daoAccess().insertRecord(note)
            return 0
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }
}

