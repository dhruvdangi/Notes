package com.wander.notes.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wander.notes.R;
import com.wander.notes.data.model.Note;
import com.wander.notes.databinding.ActivityAddNoteBinding;
import com.wander.notes.viewmodel.NoteListViewModel;
import com.wander.notes.viewmodel.NoteViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.wander.notes.view.ui.MainActivity.NOTE_CREATE_TIMESTAMP;

public class AddNoteActivity extends DaggerAppCompatActivity {

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

        observeViewModel(noteCreateTimestamp);
    }

    private void observeViewModel(String noteCreateTimestamp) {
        viewModel.getNoteObservable(noteCreateTimestamp).observe(this, note -> {
            if (note == null || note.getCreateTimeStamp().equals("")) {
                mNote = new Note("", "", String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()));
            } else mNote = note;
            binding.noteText.setText(mNote.getNoteText());
            binding.noteTitle.setText(mNote.getNoteTitle());
        });
    }

    @Override
    public void onBackPressed() {
        if (!binding.noteTitle.getText().toString().equals("") || !binding.noteText.getText().toString().equals("")) {
            mNote.setLastEditedTimestamp(String.valueOf(System.currentTimeMillis()));
            mNote.setNoteTitle(binding.noteTitle.getText().toString());
            mNote.setNoteText(binding.noteText.getText().toString());
            viewModel.updateNote(mNote);
        } else {
            viewModel.deleteNote(mNote);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            viewModel.deleteNote(mNote);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
