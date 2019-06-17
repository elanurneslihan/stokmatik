package id.pentacode.retrofit_gson_example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Users {
    @SerializedName("list")
    @Expose
    private ArrayList<User> list= null;

    public ArrayList<User> getUsers() {
        return list;
    }
}