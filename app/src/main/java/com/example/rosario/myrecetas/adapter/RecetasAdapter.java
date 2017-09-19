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

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.DeviceViewHolder>{

    private Context context;
    private List<DatosReceta> dataset;
    private OnRecetaSelectedListener onRecetaSelectedListener;

    public interface OnRecetaSelectedListener {
        void onRecetaSelected(DatosReceta datosReceta);
    }


    public RecetasAdapter (Context context, OnRecetaSelectedListener listener){
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onRecetaSelectedListener = listener;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ee","ENTROOO placeholder INFLATE...");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        DatosReceta u = dataset.get(position);
        holder.publisher.setText(u.getPublisher());
        holder.title.setText(u.getTitle());

        holder.setDeviceSelectedListener(u, onRecetaSelectedListener);

        Glide.with(context).load(u.getImageurl())
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

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        TextView publisher;
        TextView title;
        ImageView fotoImageView;
        //TextView humedadTextView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            Log.d("ENTRO A ", "CARD VIEW");
            cardView = itemView.findViewById(R.id.recetaCardView);

            publisher = (TextView) itemView.findViewById(R.id.publisher1);
            title = (TextView) itemView.findViewById(R.id.title1);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoimage);
            // ciudadTextView = (TextView) itemView.findViewById(R.id.ciudadTextView);
            //distritoTextView = (TextView) itemView.findViewById(R.id.distritoTextView);
        }

        public void setDeviceSelectedListener(final DatosReceta datosReceta, final OnRecetaSelectedListener onRecetaSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecetaSelectedListener.onRecetaSelected(datosReceta);
                }
            });
        }
    }

    public void add(DatosReceta d) {
        Log.d("deeee","ENTROOOOOOOOOOO");
       /* Log.d(">>>>>>",d.getHomeTeamName()+"");
        Log.d(">>>>>>",d.getAwayTeamName()+"");
*/

        dataset.add(d);
        notifyDataSetChanged();
        Log.d("NUMERO SIZE", String.valueOf(dataset.size()));
    }

    public void setDataset(List<DatosReceta> fixtureInfos) {
        if (fixtureInfos == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = fixtureInfos;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }
}
