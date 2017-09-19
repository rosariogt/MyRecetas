package com.example.rosario.myrecetas.service;

import com.example.rosario.myrecetas.models.DatosIngredientes;
import com.example.rosario.myrecetas.models.DatosRecipe;
import com.example.rosario.myrecetas.models.DatosRecipeIngrediente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ROSARIO on 20/9/2017.
 */

public interface DatosRecetas {
    @GET("search")
    Call<DatosRecipe> obtenerRecetas(@Query("key") String key);

    @GET("get")
    Call<DatosRecipeIngrediente> obtenerUnaRecetas(@Query("key") String key, @Query("rId") int rId);

}
