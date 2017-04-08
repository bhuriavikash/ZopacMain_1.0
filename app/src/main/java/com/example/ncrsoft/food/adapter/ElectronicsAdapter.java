package com.example.ncrsoft.food.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ncrsoft.food.R;
import com.example.ncrsoft.food.activity.Electronics;
import com.example.ncrsoft.food.fragment.ProductDetailsFragment;
import com.example.ncrsoft.food.fragment.SubCategoryFragment;
import com.example.ncrsoft.food.utils.CharecterEncode;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saurabh on 28-03-2017.
 */

public class ElectronicsAdapter extends RecyclerView.Adapter<ElectronicsAdapter.ViewHolder> {

    Context context;
    List<GetElectronicsData> getDataAdapter;
    LayoutInflater inflater;

    public ElectronicsAdapter(List<GetElectronicsData> getDataAdapter, Context context) {

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_list_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GetElectronicsData getDataAdapter1 =  getDataAdapter.get(position);



            Picasso.with(context).load(getDataAdapter1.getImageUrl()).into(holder.imageView);
            holder.product_name.setText(getDataAdapter1.getProductName());
            holder.product_price.setText(getDataAdapter1.getProductPrice());

            holder.childGridLeftLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                AppCompatActivity activity = (AppCompatActivity) v.getContext();


                ProductDetailsFragment fragment = new ProductDetailsFragment();
                Bundle args = new Bundle();

                args.putString("ProductName", getDataAdapter1.getProductName());
                args.putString("ImageUrl", getDataAdapter1.getImageUrl());
                args.putString("ProductPrice", getDataAdapter1.getProductPrice());
                args.putString("ProductDescription", getDataAdapter1.getProductDescription());
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerView, fragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{




        public ImageView imageView;
        public TextView product_name,product_price,product_description;

        LinearLayout childGridLeftLayout;


        public ViewHolder(View itemView) {

            super(itemView);
            context = itemView.getContext();


            product_name = (TextView) itemView.findViewById(R.id.textView_name);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_product);
            product_price=(TextView)itemView.findViewById(R.id.textView_price);
            product_description=(TextView)itemView.findViewById(R.id.textView_description);
            childGridLeftLayout = (LinearLayout)itemView.findViewById(R.id.common_layout);



        }



    }
}
