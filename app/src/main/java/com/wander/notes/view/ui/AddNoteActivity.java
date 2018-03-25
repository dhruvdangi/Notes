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

import static com.wander.notes.view.ui.MainActivity.NOTE_CREATE_TIMESTAMP;

public class AddNoteActivity extends DaggerAppCompatActivity{

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityAddNoteBinding binding;
    NoteViewModel viewModel;
    Note mNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        String noteCreateTimestamp = getIntent().getStringExtra(NOTE_CREATE_TIMESTAMP);

        viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NoteViewModel.class);
        viewModel.setCreateTimestamp(noteCreateTimestamp);

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getNoteObservable().observe(this, note -> {
            if (note == null || note.getCreateTimeStamp().equals("")) {
                mNote = new Note("", String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()));
            } else mNote = note;

            binding.noteText.setText(mNote.getNoteText());
        });
    }

    @Override
    public void onBackPressed() {
        mNote.setNoteText(binding.noteText.getText().toString());
        viewModel.updateNote(mNote);
        super.onBackPressed();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
