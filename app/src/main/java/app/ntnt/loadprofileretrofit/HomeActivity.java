package app.ntnt.loadprofileretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    TextView tvId,tvUsername,tvEmail,tvGender;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        User user= (User) bundle.getSerializable("user");
        tvId =findViewById(R.id.tv_id);
        tvUsername =findViewById(R.id.tv_username);
        tvEmail =findViewById(R.id.tv_email);
        tvGender =findViewById(R.id.tv_gender);
        imageView = findViewById(R.id.imageView);

        tvId.setText(String.valueOf(user.getId()));
        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        tvGender.setText(user.getGender());
        Glide.with(HomeActivity.this).
                load(user.getImages())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,UploadImage.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("user1",user);
                intent.putExtras(bundle1);
                startActivity(intent);

            }
        });
    }
}