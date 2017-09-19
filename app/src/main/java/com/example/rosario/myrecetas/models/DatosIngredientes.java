package com.example.rosario.myrecetas.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ROSARIO on 20/9/2017.
 */

public class DatosIngredientes {
    private String publisher;
    private String title;
    @SerializedName("image_url")
    private String imageUrl;
    private ArrayList<String> ingredients;


    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
