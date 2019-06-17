package id.pentacode.retrofit_gson_example;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import id.pentacode.retrofit_gson_example.models.Product;
import id.pentacode.retrofit_gson_example.models.Products;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText barkodText,stokText;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateproduct_main);
        barkodText = (EditText)findViewById(R.id.barkod);
        stokText= (EditText)findViewById(R.id.stok);
        getProducts();

        final Button button = findViewById(R.id.btn_entry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {;
                postRequestMethod();
            }
        });
    }
    protected void getProducts() {

        final ProgressDialog dialog;
        dialog = new ProgressDialog(UpdateProductActivity.this);
        dialog.setTitle("Servis çağrısı yapılıyor");
        dialog.setMessage("Lütfen bekleyiniz...");
        dialog.show();

        ApiService api = RetroClient.getApiService();

        Call<Products> call = api.getProductList();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                dialog.dismiss();
                if(response.isSuccessful()) {
                    productList = response.body().getProducts();

                } else {
                    Toast.makeText(getApplicationContext(), "Servis çağrısı sırasında hata alındı!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void removeItem (Product pItem){
        final Product item = pItem;
        final ProgressDialog dialog;
        dialog = new ProgressDialog(UpdateProductActivity.this);
        dialog.setTitle("Servis çağrısı yapılıyor");
        dialog.setMessage("Lütfen bekleyiniz...");
        dialog.show();

        ApiService api = RetroClient.getApiService();

        Call<Product> call = api.deleteProduct(pItem.getId());
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                dialog.dismiss();
                addProduct(item);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                dialog.dismiss();
                addProduct(item);
            }
        });
    }

    private void postRequestMethod() {
        Product pItem=null;

        for(Product p :productList){
            if(p.getBarcode().equalsIgnoreCase(barkodText.getText().toString())){
                pItem=p;
            }
        }
        pItem.setStock(Integer.valueOf(stokText.getText().toString()));

        removeItem(pItem);
    }

    private void addProduct(Product p){
        ApiService api = RetroClient.getApiService();
        Call<Product> call= api.addProduct(p);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                 if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Başarılı!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}
