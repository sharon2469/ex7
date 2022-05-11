package com.example.ex7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private static List<Country> countryList;
    private int selectedRow = -1;

    public CountriesAdapter(Context context) {
        countryList = CountryXMLParser.parseCountries(context);
    }


    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // instance of the inflater
        View countryView = inflater.inflate(R.layout.country_item, parent, false); // get view of the country view object
        return new CountriesViewHolder(countryView); // return county view holder
    }


    // this call by recyclerView for each row in in view
    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(countryList.get(position));

        // This listener will change the color of selected row
        holder.row_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRow = position;
                notifyDataSetChanged();
            }
        });

        if(selectedRow == position){
            holder.row_linearLayout.setBackgroundColor(Color.parseColor("#03dffc"));
        }else{
            holder.row_linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    // This function just tell to the recycler view how many items in the data
    @Override
    public int getItemCount() {
        return countryList.size();
    }



    // Each row in RecyclerView will get reference of this CountriesViewHolder
    // *** Include the function that can remove the row
    public class CountriesViewHolder extends RecyclerView.ViewHolder
    {
        private final Context   context;
        private final View      countryItem;
        private final ImageView flagImageView;
        private final TextView  nameTextView;
        private final TextView  populationTextView;
        private LinearLayout row_linearLayout;

        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            context             = itemView.getContext();
            countryItem         = itemView.findViewById(R.id.country_item);
            flagImageView       = itemView.findViewById(R.id.flagImageView);
            nameTextView        = itemView.findViewById(R.id.nameTextView);
            populationTextView  = itemView.findViewById(R.id.populationTextView);
            row_linearLayout        = itemView.findViewById(R.id.country_item);
            //*****************************************************************************************
            // This listener function call the function removeItem -> wil remove item from the ArrayList***
            //**************************************************************************************
            countryItem.setOnLongClickListener(view -> {
                removeItem(getAdapterPosition());
                return true;
            });


        }

        //******** This function bind\connect the row widgets with the data
        public void bindData(Country country) {
            flagImageView.setImageResource(getDrawableId(context, country.getFlag()));
            nameTextView.setText(country.getName());
            populationTextView.setText(country.getShorty());
        }

        private void removeItem(int position) {
            countryList.remove(position);
            notifyItemRemoved(position);// ****** this notify to the recycle that there is change in data base
        }
    }

    private static int getDrawableId(Context context, String drawableName) {
        Resources resources = context.getResources();
       return resources.getIdentifier(drawableName, "drawable", context.getPackageName());
    }

}
