package id.pentacode.retrofit_gson_example;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.pentacode.retrofit_gson_example.models.Product;
import id.pentacode.retrofit_gson_example.models.Products;
import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

     private ArrayList<Product> productList;

    private String[] list = {"","","","","","","","","","","","","","","","","","",""};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist_main);

        getProducts();

        listView=(ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, list);

        listView.setAdapter(veriAdaptoru);
    }

    protected void getProducts() {

        final ProgressDialog dialog;
        dialog = new ProgressDialog(ProductListActivity.this);
        dialog.setTitle("Servis çağrısı yapılıyor");
        dialog.setMessage("Lütfen bekleyiniz...");
        dialog.show();

        ApiService api = RetroClient.getApiService();

        Call<Products> call = api.getProductList();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                //Dismiss Dialog
                dialog.dismiss();
                if(response.isSuccessful()) {
                    productList = response.body().getProducts();
                    int index =0;
                    for (Product item :productList  ) {
                        list[index] =  "Adı    : " + item.getName()+"\n"
                                     + "Kodu   : " + item.getCode()+"\n"
                                     + "Barkod : " + item.getBarcode()+"\n"
                                     + "Stok   : " + item.getStock();
                        index++;
                    }
                 } else {
                    Toast.makeText(getApplicationContext(), "Servis çağrısı sırasında hata alındı!", Toast.LENGTH_LONG).show();
                }
                listView.invalidateViews();
            }
            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}