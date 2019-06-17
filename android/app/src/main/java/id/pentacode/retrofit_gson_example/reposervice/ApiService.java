package id.pentacode.retrofit_gson_example.reposervice;

import java.util.List;

import id.pentacode.retrofit_gson_example.models.Product;
import id.pentacode.retrofit_gson_example.models.Products;
import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.models.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/api/User")
    Call<Users> getUserList();

    @POST("/api/User")
    Call<User> addUser(@Body User user);

    @GET("/api/Product")
    Call<Products> getProductList();

    @POST("/api/Product")
    Call<Product> addProduct(@Body Product product);

    @DELETE("/api/Product/{id}")
    Call<Product> deleteProduct(@Path("id") String id);
}