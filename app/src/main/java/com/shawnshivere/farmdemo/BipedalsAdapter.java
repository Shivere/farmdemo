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

import java.util.ArrayList;
import java.util.List;

public class BipedalsAdapter extends RecyclerView.Adapter<BipedalsAdapter.MyViewHolder> implements Filterable{
    private Context context;
    private List<Animal> animalList;
    private List<Animal> animalListFiltered;
    private BipedalsAdapterListener listener;


    @NonNull
    @Override
    public BipedalsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bipedals_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, gender, type;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.bipedals_name);
            age = view.findViewById(R.id.bipedals_age);
            gender = view.findViewById(R.id.bipedals_gender);
            type = view.findViewById(R.id.bipedals_displaytype);
            thumbnail = view.findViewById(R.id.bipedals_thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAnimalSelected(animalListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
    public BipedalsAdapter(Context context, List<Animal> animalList, BipedalsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.animalList = animalList;
        this.animalListFiltered = animalList;
    }

    @Override
    public void onBindViewHolder(@NonNull BipedalsAdapter.MyViewHolder holder, int position) {
        final Animal animal = animalListFiltered.get(position);
        holder.name.setText(animal.getName());
        holder.age.setText(animal.getAge()+ context.getString(R.string.years) );
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

    public interface BipedalsAdapterListener {
        void onAnimalSelected(Animal animal);
    }
}