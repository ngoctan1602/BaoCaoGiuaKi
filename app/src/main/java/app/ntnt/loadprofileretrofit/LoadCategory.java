package app.ntnt.loadprofileretrofit;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.loadprofileretrofit.adapter.CategoryAdapter;
import app.ntnt.loadprofileretrofit.adapter.ProductAdapter;
import app.ntnt.loadprofileretrofit.model.Category;
import app.ntnt.loadprofileretrofit.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewProduct;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    ImageView imageView;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_category);
        recyclerView = findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //Lấy thông tin tài khoản
        Bundle bundle = getIntent().getExtras();
        User user= (User) bundle.getSerializable("user");
        imageView = findViewById(R.id.imageProfile);
        textView = findViewById(R.id.textView7);


        Glide.with(LoadCategory.this).load(user.getImages()).into(imageView);
        textView.setText("Xin chào "+user.getFname());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(LoadCategory.this, HomeActivity.class);
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


       //Gọi category
        APIService.apiService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoryList = response.body();
                categoryAdapter.setData(categoryList);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(LoadCategory.this,"Lỗi gọi API",Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(categoryAdapter);


        //Gọi và gán Product
        recyclerViewProduct = findViewById(R.id.rcv_product);
        productAdapter = new ProductAdapter(this);
        GridLayoutManager gridLayoutManager1= new GridLayoutManager(this,2);
        recyclerViewProduct.setLayoutManager(gridLayoutManager1);
        APIService.apiService.getLastProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                productAdapter.setData(list);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        recyclerViewProduct.setAdapter(productAdapter);
    }

}