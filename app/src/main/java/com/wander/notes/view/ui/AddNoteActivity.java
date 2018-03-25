package com.wander.notes.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.wander.notes.R;
import com.wander.notes.data.model.Note;
import com.wander.notes.databinding.ActivityAddNoteBinding;
import com.wander.notes.viewmodel.NoteListViewModel;
import com.wander.notes.viewmodel.NoteViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddNoteActivity extends DaggerAppCompatActivity{

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final NoteViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NoteViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(NoteViewModel viewModel) {
        viewModel.getNoteObservable().observe(this, note -> {
            binding.noteText.setText(note.getNoteText());
        });
    }

    private void saveNote() {
        Note note = new Note(binding.noteText.getText().toString(), String.valueOf(System.currentTimeMillis()), "1");

    }

    @Override
    public void onBackPressed() {
        saveNote();
        super.onBackPressed();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
