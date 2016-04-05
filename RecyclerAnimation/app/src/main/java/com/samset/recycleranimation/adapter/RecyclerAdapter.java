package com.samset.recycleranimation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samset.recycleranimation.R;


/**
 * Created by samset on 05/04/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
     String data[],language[];
    public RecyclerAdapter(String []dd,String []lang)
    {
        this.data=dd;
        this.language=lang;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {

        viewHolder.countryName.setText(data[i]);
        viewHolder.languageName.setText(language[i]);

        //Set image
        //GradientDrawable bgShape = (GradientDrawable) viewHolder.countryFlag.getBackground();
        //bgShape.setColor(Color.parseColor(contact.get(Person.Field.COLOR)));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView countryName, languageName;
        ImageView countryFlag;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            languageName = (TextView) itemView.findViewById(R.id.country_lang);
            countryFlag = (ImageView)itemView.findViewById(R.id.country_flag);
        }
    }

}
