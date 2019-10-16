package com.shawnshivere.farmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Note> noteList;
    private List<Note> noteListFiltered;
    private NotesAdapterListener listener;

    public NotesAdapter(Context context, List<Note> noteList, NotesAdapterListener listener){
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
        this.noteListFiltered = noteList;

    }

    @NonNull
    @Override
    public NotesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.MyViewHolder holder, final int position) {
        final Note note = noteListFiltered.get(position);
        holder.title.setText(note.getTitle());
        holder.details.setText(note.getDetails());
        holder.date.setText(note.Date);
    }

    @Override
    public int getItemCount() {
        return noteListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    noteListFiltered = noteList;
                }else {
                    List<Note> filteredList = new ArrayList<>();
                    for (Note row : noteList){
                        // title and date match condition. this might differ depending on your requirement
                        // here we are looking for name or date match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getDate().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    noteListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = noteListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                noteListFiltered = (ArrayList<Note>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, details, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_Title);
            details = itemView.findViewById(R.id.note_Details);
            date = itemView.findViewById(R.id.note_Date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnNoteSelected(noteListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface NotesAdapterListener{
        void OnNoteSelected(Note note);
    }
}
