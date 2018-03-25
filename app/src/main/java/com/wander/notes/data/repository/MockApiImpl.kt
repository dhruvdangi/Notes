package com.wander.notes.data.repository

import com.wander.notes.data.model.Note
import org.json.JSONObject
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate
import java.util.*

class MockApiImpl(val delegate: BehaviorDelegate<RESTService>) : RESTService {
    override fun postNote(userId: String?): Call<JSONObject> {
        val jsonObject = JSONObject()
        jsonObject.put("success", true)
        return delegate.returningResponse(jsonObject).postNote(userId)
    }

    override fun getNoteList(userId: String?): Call<List<Note>>? {

        val noteList = ArrayList<Note>()
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584533", "1522006584533"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584534", "1522006584534"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584535", "1522006584535"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584536", "1522006584536"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584537", "1522006584537"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584538", "1522006584538"))
        noteList.add(Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Proin vitae ligula sit amet purus lacinia lobortis in et purus. Praesent varius dictum nunc et euismod. Etiam interdum, diam id ", "1522006584539", "1522006584539"))

        return delegate.returningResponse(noteList).getNoteList(userId)
    }
}
