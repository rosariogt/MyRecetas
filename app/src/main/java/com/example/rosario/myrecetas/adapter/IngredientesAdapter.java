package com.example.rosario.myrecetas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rosario.myrecetas.R;
import com.example.rosario.myrecetas.models.DatosIngredientes;
import com.example.rosario.myrecetas.models.DatosReceta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROSARIO on 20/9/2017.
 */

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.DeviceViewHolder>{
    private Context context;
    private List<DatosIngredientes> dataset;


    public IngredientesAdapter(Context context){
        this.dataset = new ArrayList<>();
        this.context = context;
    }
    @Override
    public IngredientesAdapter.DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ee","ENTROOO placeholder INFLATE...");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingrediente, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        DatosIngredientes u = dataset.get(position);
        holder.publisher.setText(u.getPublisher());
        holder.title.setText(u.getTitle());

        Glide.with(context).load(u.getImageUrl())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
        // holder.humedadTextView.setText(u.getDate()+"");
        Log.d("ee","ENTROOO placeholder onBindViewHolder");
        // holder.distritoTextView.setText(u.getDistrito());

        //holder.setDeviceSelectedListener(u, onUnidadEducativaSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
/*
    @Override
    public int getItemCount() {
        return dataset.size();
    }*/

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        TextView publisher;
        TextView title;
        ImageView fotoImageView;
        //TextView humedadTextView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            /*Log.d("ENTRO A ", "CARD VIEW");
            cardView = itemView.findViewById(R.id.recetaCardView);
*/
            publisher = (TextView) itemView.findViewById(R.id.publisher2);
            title = (TextView) itemView.findViewById(R.id.title2);
            fotoImageView = (ImageView) itemView.findViewById(R.id.imageView3);

        }
    }

    public void add(DatosIngredientes d) {
        Log.d("deeee","ENTROOOOOOOOOOO");

       // dataset = d;
/*       dataset.setImageUrl(d.getImageUrl());
        dataset.setPublisher(d.getPublisher());
        dataset.setTitle(d.getTitle());*/
        Log.d("PUBLISHERRRRR",d.getPublisher());
        dataset.add(d);
        notifyDataSetChanged();
        //Log.d("NUMERO SIZE", String.valueOf(dataset.size()));
    }

    public void setDataset(ArrayList<DatosIngredientes> datosIngredientes) {
        if (datosIngredientes == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = datosIngredientes;
        }
        notifyDataSetChanged();
    }


    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

}
