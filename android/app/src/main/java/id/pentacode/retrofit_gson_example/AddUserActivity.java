package id.pentacode.retrofit_gson_example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import id.pentacode.retrofit_gson_example.models.Product;
import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.models.Users;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import id.pentacode.retrofit_gson_example.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private ArrayList<User> userList;

    private EditText userText;
    private EditText passwdText,passwdText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser_main);
        userText = (EditText)findViewById(R.id.input_email);
        passwdText = (EditText)findViewById(R.id.input_password);
        passwdText2 = (EditText)findViewById(R.id.input_password2);

        final Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(passwdText.getText().toString().equals(passwdText2.getText().toString()) &&
                    passwdText.getText().toString()!= null){
                postRequestMethod();
            }else{
                Toast.makeText(getApplicationContext(),"Şifreler uyuşmuyor !",Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    private void postRequestMethod() {
        final ProgressDialog dialog;
        dialog = new ProgressDialog(AddUserActivity.this);
        dialog.setMessage("Lütfen Bekleyiniz");
        dialog.show();

        ApiService api = RetroClient.getApiService();
        User u = new User();
        u.setName(userText.getText().toString());
        u.setPasswd(passwdText.getText().toString());

        Call<User> call= api.addUser(u);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dialog.dismiss();
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Kullanıcı başarıyla oluşturuldu !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"hata.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
