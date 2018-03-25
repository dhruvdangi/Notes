package com.wander.notes.di;

import com.wander.notes.viewmodel.NoteListViewModel;
import com.wander.notes.viewmodel.NoteViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    NoteListViewModel noteListViewModel();
    NoteViewModel noteViewModel();
}