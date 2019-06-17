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

import id.pentacode.retrofit_gson_example.models.User;
import id.pentacode.retrofit_gson_example.models.Users;
import id.pentacode.retrofit_gson_example.reposervice.ApiService;
import id.pentacode.retrofit_gson_example.retrofit.RetroClient;
import id.pentacode.retrofit_gson_example.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        final Button button = findViewById(R.id.urunkayit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AddProductActivity.class));
            }
        });

        final Button delete= findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),DeleteProductActivity.class));
            }
        });

        final Button stockbtn= findViewById(R.id.stock);
        stockbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ProductListActivity.class));
            }
        });

        final Button upd= findViewById(R.id.update);
        upd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),UpdateProductActivity.class));
            }
        });
    }
}
