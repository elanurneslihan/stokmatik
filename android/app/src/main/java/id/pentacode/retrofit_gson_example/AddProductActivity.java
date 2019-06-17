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
import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {

    private EditText adiText,kodText,barkodText,stokText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct_main);
        adiText = (EditText)findViewById(R.id.adi);
        kodText = (EditText)findViewById(R.id.kod);
        barkodText = (EditText)findViewById(R.id.barkod);
        stokText= (EditText)findViewById(R.id.stok);

        final Button button = findViewById(R.id.btn_entry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {;
                postRequestMethod();
            }
        });
    }

    private void postRequestMethod() {
        final ProgressDialog dialog;
        dialog = new ProgressDialog(AddProductActivity.this);
        dialog.setMessage("Lütfen Bekleyiniz");
        dialog.show();

        ApiService api = RetroClient.getApiService();
        Product p = new Product();
        p.setBarcode(barkodText.getText().toString());
        p.setCode(kodText.getText().toString());
        p.setName(adiText.getText().toString());
        p.setStock(Integer.parseInt(stokText.getText().toString()));
        Call<Product> call= api.addProduct(p);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Ürün ekleme başarılı!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
