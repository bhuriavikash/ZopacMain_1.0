package com.example.ncrsoft.food.adapter;

/**
 * Created by Saurabh on 25-03-2017.
 */
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.example.ncrsoft.food.activity.Appliances;
import com.example.ncrsoft.food.activity.BabyAndKids;
import com.example.ncrsoft.food.activity.BooksAndMore;
import com.example.ncrsoft.food.activity.Electronics;
import com.example.ncrsoft.food.activity.HomeAndFurniture;
import com.example.ncrsoft.food.activity.Men;
import com.example.ncrsoft.food.activity.Women;
import com.example.ncrsoft.food.fragment.PrivacyFragment;
import com.example.ncrsoft.food.fragment.SubCategoryFragment;
import com.example.ncrsoft.food.utils.CharecterEncode;
import com.squareup.picasso.Picasso;

import java.util.List;








public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {





    Context context;


    List<GetDataAdapter> getDataAdapter;


    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context) {

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);



    holder.StatusTextView.setText(getDataAdapter1.getStatusCode());

    holder.CatNameTextView.setText(getDataAdapter1.getCategoryName());

    Picasso.with(context).load(getDataAdapter1.getImageUrl()).into(holder.imageView);


       holder.childGridLeftLayout.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View v) {


               AppCompatActivity activity = (AppCompatActivity) v.getContext();


               SubCategoryFragment fragment = new SubCategoryFragment();
               Bundle args = new Bundle();
               args.putString("index", CharecterEncode.decodeChar(getDataAdapter1.getCategoryId()));
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



        public TextView StatusTextView;
        public TextView CatIdTextView;
        public TextView CatNameTextView;
        public ImageView imageView;
        LinearLayout childGridLeftLayout;


        public ViewHolder(View itemView) {

            super(itemView);
            context = itemView.getContext();

            StatusTextView = (TextView) itemView.findViewById(R.id.textView2);
            CatIdTextView = (TextView) itemView.findViewById(R.id.textView4);
            CatNameTextView = (TextView) itemView.findViewById(R.id.textView6);
            imageView = (ImageView)itemView.findViewById(R.id.imageView1);
            childGridLeftLayout = (LinearLayout)itemView.findViewById(R.id.gridmore);



        }



    }








}
