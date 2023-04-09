package app.ntnt.loadprofileretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.loadprofileretrofit.model.Category;
import app.ntnt.loadprofileretrofit.model.Product;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(APIService.class);

    //appfoods/registrationapi.php?apicall=login
    //shoppingapp/registrationapi.php?apicall=login
    @FormUrlEncoded
    @POST("appfoods/registrationapi.php?apicall=login")
    Call<Resp> getUser(
            @Field("username") String username,
            @Field("password") String password);

    @GET("appfoods/categories.php")
    Call<List<Category>> getAllCategory();

    @GET("appfoods/lastproduct.php")
    Call<List<Product>> getLastProduct();


    @FormUrlEncoded
    @POST("appfoods/getcategory.php")
    Call<List<Product>> getProductById(
            @Field("idcategory") int idcategory);


    //http://app.iotstar.vn/appfoods/newmealdetail.php
    @FormUrlEncoded
    @POST("appfoods/newmealdetail.php")
    Call<NewResp> getDetailProduct(
            @Field("id") int id);
    //Load ảnh
    @Multipart
    @POST("/appfoods/updateimages.php")
    Call<NewRespUser> upload(@Part("id") RequestBody id, @Part MultipartBody.Part images);

}
