package com.example.rosario.myrecetas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rosario.myrecetas.adapter.IngredientesAdapter;
import com.example.rosario.myrecetas.adapter.RecetasAdapter;
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

public class RecetasActivity extends AppCompatActivity implements RecetasAdapter.OnRecetaSelectedListener {

    private RecyclerView recyclerView;
    private RecetasAdapter recetasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_main);

        recyclerView = (RecyclerView) findViewById(R.id.recetaRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recetasAdapter = new RecetasAdapter(this, this);

        recyclerView.setAdapter(recetasAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ENTRO","ON START");
        //swipeRefreshLayout.setRefreshing(true);
        cargarDatos();
    }

    private void cargarDatos() {

        Log.d("ENTRO","CARGA DATOS");
        //AQUI SE HACE LA PETICION, AGARRAR LOS DATOS
        //PRIMERO AGARRAMOS UNA REFERENCIA DEL SERVICIO
        //escribir el nombre de la interface

        DatosRecetas service= ServiceGenerator.createService(DatosRecetas.class);
        Log.d("OBTIENE", String.valueOf(service));

        //escribimos la llamada lo vamos a guardar en una variable
        Call<DatosRecipe> call = service.obtenerRecetas("e634c96f6795762652746257455ef28a");
        Log.d("ENTRO", String.valueOf(call));
        // ahora ponemos el codigo feo donde verifica
        call.enqueue(new Callback<DatosRecipe>() {

            @Override
            public void onResponse(Call<DatosRecipe> call, Response<DatosRecipe> response) {

                if (response.isSuccessful()){
                    Log.d("ENTRO","A IF LL");
                    //body devuelve
                    List<DatosReceta> lista = (response.body().getRecipes());

                    //fxtureAdapter.setDataset(lista);
                    Log.d("TAMANIO",lista.size()+"");
                    for (int i = 0; i< 4; i++){
                        Log.d("valor i",lista.get(i).getPublisher());
                        Log.d("valor i",lista.get(i).getTitle());
                    }
                    recetasAdapter.setDataset(lista);
                }else{

                }
            }
            @Override
            public void onFailure(Call<DatosRecipe> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRecetaSelected(DatosReceta datosReceta) {
        Intent intent = new Intent(this, IngredientesActivity.class);
        intent.putExtra("id", datosReceta.getId());
        Log.d("ID>>>>>>>>>>>>",datosReceta.getId());
        startActivity(intent);
    }
}
