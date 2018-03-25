package com.wander.notes.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wander.notes.R;
import com.wander.notes.data.model.Note;
import com.wander.notes.databinding.NoteListItemBinding;
import com.wander.notes.view.ClickListener;

import java.util.ArrayList;
import java.util.Collections;
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
        Collections.sort(notes, ((o1, o2) -> {
            if (Long.valueOf(o1.getLastEditedTimestamp()) > Long.valueOf(o2.getLastEditedTimestamp())) return -1;
            if (Long.valueOf(o1.getLastEditedTimestamp()) < Long.valueOf(o2.getLastEditedTimestamp())) return 1;
            else return 0;
        }));
        mNotes.clear();
        mNotes.addAll(notes);
//        for (int i = 0; i<notes.size();i++) {
//            boolean isPresent = false;
//            Note note = notes.get(i);
//            for (int j = 0; j<mNotes.size();j++){
//                if (mNotes.get(j).getCreateTimeStamp().equals(note.getCreateTimeStamp())) {
//                    isPresent = true;
//                    if (!mNotes.get(j).getLastEditedTimestamp().equals(note.getLastEditedTimestamp())) {
//                        mNotes.remove(j);
//                        mNotes.set(0, note);
//                    }
//                }
//            }
//            if (!isPresent)
//                mNotes.add(note);
//        }
        notifyDataSetChanged();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        final NoteListItemBinding binding;
        public NotesViewHolder(NoteListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

