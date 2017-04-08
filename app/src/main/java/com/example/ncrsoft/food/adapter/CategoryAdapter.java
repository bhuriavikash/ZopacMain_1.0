package com.example.ncrsoft.food.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ncrsoft.food.R;
import com.example.ncrsoft.food.activity.Electronics;
import com.example.ncrsoft.food.fragment.SubCategoryFragment;
import com.example.ncrsoft.food.utils.CharecterEncode;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saurabh on 06-04-2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    Context context;


    List<GetDataAdapter> getDataAdapter;

    public CategoryAdapter(List<GetDataAdapter> getDataAdapter, Context context) {

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category, parent, false);


        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override

    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, final int position) {


        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);



        holder.CategoryTextView.setText(getDataAdapter1.getCategoryName());


        holder.childGridLeftLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String str =  String.valueOf(position+1);
                Bundle bundle = new Bundle();
                bundle.putString("id",str);
                Intent intent = new Intent(context,Electronics.class);
                intent.putExtras(bundle);
                context.startActivity(intent);


                /*AppCompatActivity activity = (AppCompatActivity) v.getContext();


                SubCategoryFragment fragment = new SubCategoryFragment();
                Bundle args = new Bundle();
                args.putString("index", CharecterEncode.decodeChar(getDataAdapter1.getCategoryId()));
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerView, fragment).addToBackStack(null).commit();*/


            }
        });

    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{



        public TextView CategoryTextView;

        LinearLayout childGridLeftLayout;


        public ViewHolder(View itemView) {

            super(itemView);
            context = itemView.getContext();

            CategoryTextView = (TextView) itemView.findViewById(R.id.textParent);

            childGridLeftLayout = (LinearLayout)itemView.findViewById(R.id.catLayout);



        }



    }



}
