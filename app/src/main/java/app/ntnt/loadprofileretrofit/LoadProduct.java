package app.ntnt.loadprofileretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import app.ntnt.loadprofileretrofit.adapter.ProductAdapter;
import app.ntnt.loadprofileretrofit.model.Category;
import app.ntnt.loadprofileretrofit.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadProduct extends AppCompatActivity {
    private TextView textView;
    private RecyclerView recyclerView;
    ProductAdapter productAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product);
        textView = findViewById(R.id.textView8);
        recyclerView = findViewById(R.id.product_by_id_category);

        productAdapter = new ProductAdapter(this);
        GridLayoutManager gridLayoutManager1= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager1);


        Bundle bundle = getIntent().getExtras();
        int idCategory=  (int)bundle.getSerializable("idcategory");
        textView.setText(String.valueOf(idCategory));




        APIService.apiService.getProductById(idCategory).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                productAdapter.setData(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        recyclerView.setAdapter(productAdapter);

    }
}