package com.wander.notes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.wander.notes.data.model.Note;
import com.wander.notes.data.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

public class NoteViewModel extends AndroidViewModel{

    private MutableLiveData<Note> mNote;
    private NoteRepository mNoteRepository;

    @Inject
    public NoteViewModel(@NonNull NoteRepository noteRepository, @NonNull Application application) {
        super(application);
        mNoteRepository = noteRepository;
        mNote = new MutableLiveData<>();
        mNote.setValue(new Note());
    }

    public void setCreateTimestamp(String timestamp) {
//        mNote = mNoteRepository.getNoteList("");

    }

    public void updateNote(Note note) {
        mNote.setValue(note);
        mNoteRepository.updateNote(note);
    }

    public LiveData<Note> getNoteObservable() {
        return mNote;
    }

}

