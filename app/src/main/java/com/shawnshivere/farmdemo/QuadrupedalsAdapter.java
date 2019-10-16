package com.shawnshivere.farmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class QuadrupedalsAdapter extends RecyclerView.Adapter<QuadrupedalsAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Animal> animalList;
    private List<Animal> animalListFiltered;
    private AnimalsAdapterListener listener;


    @NotNull
    @Override
    public QuadrupedalsAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, gender, type;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
            gender = view.findViewById(R.id.gender);
            type = view.findViewById(R.id.displaytype);
            thumbnail = view.findViewById(R.id.thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAnimalSelected(animalListFiltered.get(getAdapterPosition()));
                }
            });

        }
    }
    public QuadrupedalsAdapter(Context context, List<Animal> animalList, AnimalsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.animalList = animalList;
        this.animalListFiltered = animalList;
    }

    @Override
    public void onBindViewHolder(@NonNull QuadrupedalsAdapter.MyViewHolder holder, final int position) {
        final Animal animal = animalListFiltered.get(position);
        holder.name.setText(animal.getName());
        holder.age.setText(animal.getAge()+ " years" );
        holder.gender.setText(animal.getGender());
        holder.type.setText(animal.getType());

        Glide.with(context)
                .load(animal.getThumbnail())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return animalListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    animalListFiltered = animalList;
                } else {
                    List<Animal> filteredList = new ArrayList<>();
                    for (Animal row : animalList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getType().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    animalListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = animalListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                animalListFiltered = (ArrayList<Animal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface AnimalsAdapterListener {
        void onAnimalSelected(Animal animal);
    }
}
