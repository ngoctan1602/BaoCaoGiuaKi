package app.ntnt.loadprofileretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import app.ntnt.loadprofileretrofit.database.Database;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {
    TextView textView;
    Database database;
    TextView total;
    TextView detailProduct;
    ImageView imageView;
    Button btnAdd,btnSub;
    Button addCart;
    private int currentTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        textView = findViewById(R.id.tv_name_detail_product);
        detailProduct =findViewById(R.id.tv_detail_product);
        imageView = findViewById(R.id.imageProfile2);

        database = new Database(this,"cart.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Cart(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idProduct INTEGER, idUser INTEGER, total INTEGER)");


        int idUser = SharedPrefManager.getInstance(this).getUser().getId();
        //Lấy id sản phẩm
        Bundle bundle = getIntent().getExtras();
        int idProduct=  (int)bundle.getSerializable("id");

        total = findViewById(R.id.tv_total);
        total.setText(String.valueOf(loadTotal(idProduct,idUser)));
        total.setEnabled(false);
       // currentTotal = Integer.parseInt(total.getText().toString());
        addCart = findViewById(R.id.bt_add_to_cart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTotal=loadDatabase(idProduct,idUser,Integer.parseInt(total.getText().toString()));
                total.setText(String.valueOf(currentTotal));
            }
        });
        btnAdd = findViewById(R.id.bt_add);
        btnSub = findViewById(R.id.bt_sub);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTotal+=1;
                total.setText(String.valueOf(currentTotal));
                if(currentTotal>1)
                {
                    btnSub.setEnabled(true);
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTotal!=1) {
                    btnSub.setEnabled(true);
                    currentTotal -= 1;
                    total.setText(String.valueOf(currentTotal));
                    if(currentTotal==1)
                        btnSub.setEnabled(false);
                }

            }
        });



        //textView.setText(String.valueOf(idProduct));

        APIService.apiService.getDetailProduct(idProduct).enqueue(new Callback<NewResp>() {
            @Override
            public void onResponse(Call<NewResp> call, Response<NewResp> response) {
                if(response.body().isSuccess()==true)
                {
                    List<NewProduct> product = response.body().getResult();
                    NewProduct newProduct = product.get(0);
                    detailProduct.setText(newProduct.getInstructions());
                    textView.setText(newProduct.getMeal());
                    Glide.with(DetailProductActivity.this).load(newProduct.getStrmealthumb()).into(imageView);

                }

            }

            @Override
            public void onFailure(Call<NewResp> call, Throwable t) {

            }
        });
    }


    private int loadDatabase(int idProduct, int idUser,int total)
    {

        Cursor cursor= database.GetData("Select * from Cart where idProduct="+idProduct+" and idUser= "+idUser+" ");
        if(cursor.getCount() >0) {
            database.QueryData("UPDATE Cart SET total=+"+total+"");
            Cursor cursor1= database.GetData("Select * from Cart where idProduct="+idProduct+" and idUser= "+idUser+" ");
            cursor1.moveToFirst();
            return cursor1.getInt(3);
        }
        else {
            database.QueryData("INSERT INTO Cart VALUES(null,"+idProduct+","+idUser+","+total+")");
            Cursor cursor1= database.GetData("Select * from Cart where idProduct="+idProduct+" and idUser= "+idUser+" ");
            cursor1.moveToFirst();
            return cursor1.getInt(3);
        }

    }

    private int loadTotal(int idProduct, int idUser)
    {

        Cursor cursor= database.GetData("Select * from Cart where idProduct="+idProduct+" and idUser= "+idUser+" ");
        if(cursor.getCount() >0) {

            cursor.moveToFirst();
            return cursor.getInt(3);
        }
        return 1;

    }
}