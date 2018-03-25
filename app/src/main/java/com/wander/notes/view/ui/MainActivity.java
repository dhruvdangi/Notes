package com.wander.notes.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.wander.notes.R;
import com.wander.notes.data.model.Note;
import com.wander.notes.databinding.ActivityMainBinding;
import com.wander.notes.view.adapter.NotesAdapter;
import com.wander.notes.viewmodel.NoteListViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity{

    private ActivityMainBinding binding;
    public static final int ADD_NOTE_ACTIVITY = 1;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    NotesAdapter mNotesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        mNotesAdapter = new NotesAdapter(note -> Log.i("MainActivity", note.getNoteText()));
        binding.setView(this);
        binding.recyclerView.setAdapter(mNotesAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final NoteListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NoteListViewModel.class);

        observeViewModel(viewModel);
    }

    public void onNoteClicked(Note note) {
        Log.i("note", note.getNoteText());
    }

    public void onAddNote() {
        startActivityForResult(new Intent(this, AddNoteActivity.class), ADD_NOTE_ACTIVITY);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_ACTIVITY) {

        }
    }

    private void observeViewModel(NoteListViewModel viewModel) {
        viewModel.getNoteListObservable().observe(this, notes -> {
            if (notes != null) {
                mNotesAdapter.addNotes(notes);
            }
        });
    }

}
