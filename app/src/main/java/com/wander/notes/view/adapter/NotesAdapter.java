package com.wander.notes.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wander.notes.R;
import com.wander.notes.data.model.Note;
import com.wander.notes.databinding.NoteListItemBinding;
import com.wander.notes.view.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    List<Note> mNotes;
    ClickListener mClickListener;
    public NotesAdapter(ClickListener clickListener) {
        mClickListener = clickListener;
        mNotes = new ArrayList<>();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoteListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.note_list_item,
                        parent, false);

        binding.setListener(mClickListener);

        return new NotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.binding.setNote(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void addNotes(List<Note> notes) {
        mNotes.addAll(notes);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        final NoteListItemBinding binding;
        public NotesViewHolder(NoteListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

