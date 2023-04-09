package app.ntnt.loadprofileretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView tvUsername;
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String a="trung2";
        String b="123";
        button = findViewById(R.id.button);
        tvUsername = findViewById(R.id.tv_username);
        tvPassword= findViewById(R.id.tv_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tvUsername.getText().toString().trim();
                String password = tvPassword.getText().toString().trim();

                APIService.apiService.getUser(username,password).enqueue(new Callback<Resp>() {
                    @Override
                    public void onResponse(Call<Resp> call, Response<Resp> response) {
                        Toast.makeText(MainActivity.this,"Call Api successfully",Toast.LENGTH_LONG).show();
                        Resp resp =response.body();
                        if(resp.getUser()!=null) {
                            Bundle bundle = new Bundle();

                            bundle.putSerializable("user", resp.getUser());
                            Intent intent = new Intent(MainActivity.this, LoadCategory.class);
                            intent.putExtras(bundle);
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(resp.getUser());
                            startActivity(intent);
                        }
                        Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Resp> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Call Api failed",Toast.LENGTH_LONG).show();
                        button.setText("Thất bại");
                    }
                });
            }
        });




    }
}