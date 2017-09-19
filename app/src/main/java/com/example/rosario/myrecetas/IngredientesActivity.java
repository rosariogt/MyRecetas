package com.example.rosario.myrecetas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rosario.myrecetas.models.DatosIngredientes;
import com.example.rosario.myrecetas.models.DatosReceta;
import com.example.rosario.myrecetas.models.DatosRecipe;
import com.example.rosario.myrecetas.models.DatosRecipeIngrediente;
import com.example.rosario.myrecetas.service.DatosRecetas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ROSARIO on 20/9/2017.
 */

public class IngredientesActivity extends AppCompatActivity {

    private TextView title;
    private TextView publisher;
    private ImageView imagen;
    private TextView ingredientes;

    private int id;
    private String id_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrediente);
        //datosIngredientes = new DatosIngredientes();
        //ingredientesAdapter = new IngredientesAdapter(this);
        title = (TextView) findViewById(R.id.title2);
        publisher = (TextView) findViewById(R.id.publisher2);
        imagen = (ImageView) findViewById(R.id.imageView3);
        ingredientes = (TextView) findViewById(R.id.ingredientes);

        id_1 = getIntent().getStringExtra("id");

        id=Integer.parseInt(id_1);

        DatosRecetas service= ServiceGenerator.createService(DatosRecetas.class);
        Log.d("OBTIENE", String.valueOf(service));

        Call<DatosRecipeIngrediente> call = service.obtenerUnaRecetas("e634c96f6795762652746257455ef28a",id);
        Log.d("ENTRO", String.valueOf(call));

        call.enqueue(new Callback<DatosRecipeIngrediente>() {

            @Override
            public void onResponse(Call<DatosRecipeIngrediente> call, Response<DatosRecipeIngrediente> response) {

                if (response.isSuccessful()){
                    Log.d("ENTRO","A IF LL");
                    //body devuelve
                    DatosIngredientes d=response.body().getRecipe();
                    String url = d.getImageUrl();

                    Log.d("URL",url);
                    title.setText(d.getTitle());
                    publisher.setText(d.getPublisher());
                    String a="";
                    int cont = 1;
                    for(int i=0; i < d.getIngredients().size(); i++)
                    {
                        Log.d("INGREDIENTE",d.getIngredients().get(i));
                        a=a+cont+". "+d.getIngredients().get(i)+"\n\n";
                        cont++;
                    }
                    ingredientes.setText(a);
                    Glide.with(IngredientesActivity.this).load(url).centerCrop()
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imagen);

                }else{

                }
            }
            @Override
            public void onFailure(Call<DatosRecipeIngrediente> call, Throwable t) {

            }
        });
    }
}
