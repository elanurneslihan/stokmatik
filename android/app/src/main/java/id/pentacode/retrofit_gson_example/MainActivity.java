package id.pentacode.retrofit_gson_example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import id.pentacode.retrofit_gson_example.models.Product;
import id.pentacode.retrofit_gson_example.models.Products;
import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.models.Users;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import id.pentacode.retrofit_gson_example.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> userList;
    private ArrayList<Product> productList;

    private EditText userText;
    private EditText passwdText;
    private TextView signupText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userText = (EditText)findViewById(R.id.input_email);
        passwdText = (EditText)findViewById(R.id.input_password);
        signupText= (TextView) findViewById(R.id.link_signup);

        final Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {;
                checkUser();
                //postRequestMethod();
             }
        });
        signupText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {;
                startActivity(new Intent(getBaseContext(), AddUserActivity.class));
            }
        });

    }

    protected void getProducts() {

            final ProgressDialog dialog;
            dialog = new ProgressDialog(MainActivity.this);
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
                        Toast.makeText(getApplicationContext(), "Hatali giriş yaptınız!", Toast.LENGTH_LONG).show();
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


    protected void checkUser() {

        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Servis çağrısı yapılıyor");
            dialog.setMessage("Lütfen bekleyiniz...");
            dialog.show();

            ApiService api = RetroClient.getApiService();

            Call<Users> call = api.getUserList();
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    //Dismiss Dialog
                    dialog.dismiss();
                    if(response.isSuccessful()) {
                        userList = response.body().getUsers();
                        boolean flag = false;

                        for(int i = 0;i<userList.size();i++){
                            if(userList.get(i).getName().equals(userText.getText().toString() ) &&
                                    userList.get(i).getPaswd().equals(passwdText.getText().toString() )){
                                Toast.makeText(getApplicationContext(), "Kullanıcı girişi başarılı!", Toast.LENGTH_LONG).show();
                                flag = true;

                                Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                                startActivity(intent);
                            }
                        }
                        if(flag == false){
                            Toast.makeText(getApplicationContext(), "Hatali giriş yaptınız!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Servis çağrısı sırasında hata alındı!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            //Snackbar.make(findViewById(R.id.layoutMain), "Check your internet connection!", Snackbar.LENGTH_LONG).show();
        }
    }

}
