package com.example.rosario.myrecetas.models;

import com.google.gson.annotations.SerializedName;
/**
 * Created by ROSARIO on 20/9/2017.
 */

public class DatosReceta {
    private String publisher;
    private String title;
    @SerializedName("recipe_id")
    private String id;
    @SerializedName("image_url")
    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
