package app.ntnt.loadprofileretrofit.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import app.ntnt.loadprofileretrofit.LoadCategory;
import app.ntnt.loadprofileretrofit.LoadProduct;
import app.ntnt.loadprofileretrofit.R;
import app.ntnt.loadprofileretrofit.model.Category;


public class CategoryAdapter extends  Adapter<CategoryAdapter.CategoryViewHolder> {
    private LoadCategory context;
    List<Category> categories;
    //private IClickItem iClickItem;

    public CategoryAdapter(LoadCategory context) {
        this.context = context;
    }
    public void setData(List<Category> categories)//,IClickItem iClickItem)
    {
        //this.iClickItem=iClickItem;
        this.categories = categories;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        if(category!=null)
        {
            holder.txtName.setText(category.getName());
            Glide.with(context).load(category.getImages()).into(holder.imageView);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context, LoadProduct.class);
                    bundle.putSerializable("idcategory",category.getId());
                    intent.putExtras(bundle);
                    startActivity(context,intent,bundle);

                }
            });
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
        private RelativeLayout relativeLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tv_nameCategory);
            imageView = itemView.findViewById(R.id.img_imageCategory);
            relativeLayout = itemView.findViewById(R.id.rlt_category);

        }
    }
}
