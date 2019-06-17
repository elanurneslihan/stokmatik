package id.pentacode.retrofit_gson_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {
    @SerializedName("list")
    @Expose
    private ArrayList<Product> list= null;

    public ArrayList<Product> getProducts() {
        return list;
    }
}