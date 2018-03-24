package com.wander.notes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.wander.notes.data.model.Note;
import com.wander.notes.data.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

public class NoteListViewModel extends AndroidViewModel{

    private final LiveData<List<Note>> noteListObservable;

    @Inject
    public NoteListViewModel(@NonNull NoteRepository noteRepository, @NonNull Application application) {
        super(application);

        noteListObservable = noteRepository.getNoteList("Google");
    }

    public LiveData<List<Note>> getNoteListObservable() {
        return noteListObservable;
    }
}
