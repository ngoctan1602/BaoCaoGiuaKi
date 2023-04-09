package app.ntnt.loadprofileretrofit.adapter;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import app.ntnt.loadprofileretrofit.LoadCategory;
import app.ntnt.loadprofileretrofit.LoadProduct;
import app.ntnt.loadprofileretrofit.R;
import app.ntnt.loadprofileretrofit.model.Product;


public class NewProductAdapter extends  Adapter<NewProductAdapter.CategoryViewHolder> {
    private LoadCategory context;
    private LoadProduct loadProductContent;
    private boolean a =false;
    List<Product> categories;
    //private IClickItem iClickItem;

    public NewProductAdapter(LoadCategory context) {
        a=true;
        this.context = context;
    }
    public NewProductAdapter(LoadProduct loadProductContent) {
        a=false;
        this.loadProductContent = loadProductContent;
    }
    public void setData(List<Product> categories)//,IClickItem iClickItem)
    {
        //this.iClickItem=iClickItem;
        this.categories = categories;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.CategoryViewHolder holder, int position) {
        Product category = categories.get(position);
        if(category!=null)
        {
            holder.txtName.setText(category.getStrMeal());
            if(a==true)
                 Glide.with(context).load(category.getStrMealThumb()).into(holder.imageView);
            if(a==false)
                Glide.with(loadProductContent).load(category.getStrMealThumb()).into(holder.imageView);
        }
    }





    @Override
    public int getItemCount() {
        if(categories!=null)
            return categories.size();
        return 0;
    }

    public class CategoryViewHolder extends ViewHolder {

        private TextView txtName;
        private ImageView imageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tv_nameProduct);
            imageView = itemView.findViewById(R.id.img_product);


        }
    }
}
