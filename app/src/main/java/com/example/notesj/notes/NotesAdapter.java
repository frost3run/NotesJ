package com.example.notesj.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesj.R;

import java.util.ArrayList;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Note> notes;
    private OnNoteClickListener onNoteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(int position);
        void onLongNoteClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewDescription.setText(note.getDescription());
        holder.textViewDayOfWeek.setText(Note.getDayToString(note.getDayOfWeek() + 1));
        int colorId = 0;
        switch (note.getPriority()) {
            case 1: colorId = holder.itemView.getResources().getColor(android.R.color.holo_red_light);
                    break;
            case 2: colorId = holder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                    break;
            default: colorId = holder.itemView.getResources().getColor(android.R.color.holo_green_light);
                    break;
        }
        holder.textViewTitle.setBackgroundColor(colorId);

        holder.itemView.setOnClickListener(v -> {
            if (onNoteClickListener != null)
                onNoteClickListener.onNoteClick(position);
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (onNoteClickListener != null)
                onNoteClickListener.onLongNoteClick(position);
            return true;
        });
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewDayOfWeek;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewDayOfWeek = (TextView) itemView.findViewById(R.id.textViewDayOfWeek);
        }
    }
}
