package com.wander.notes.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.wander.notes.R;
import com.wander.notes.databinding.ActivityMainBinding;
import com.wander.notes.view.ClickListener;
import com.wander.notes.view.adapter.NotesAdapter;
import com.wander.notes.viewmodel.NoteListViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity{

    private ActivityMainBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    NotesAdapter mNotesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mNotesAdapter = new NotesAdapter(note -> Log.i("MainActivity", note.getNoteText()));

        binding.recyclerView.setAdapter(mNotesAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final NoteListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NoteListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(NoteListViewModel viewModel) {
        viewModel.getNoteListObservable().observe(this, notes -> {
            if (notes != null) {
                mNotesAdapter.addNotes(notes);
            }
        });
    }

}
