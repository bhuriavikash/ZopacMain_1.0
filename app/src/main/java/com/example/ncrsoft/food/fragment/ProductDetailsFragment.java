package com.example.ncrsoft.food.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ncrsoft.food.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    public View view_product;
    Context context;

    String product_name;
    String image_url;
    String product_price;
    String product_description;
    TextView textView_productName,textView_productPrice,textView_productDescription;
    ImageView imageView_product;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_product = inflater.inflate(R.layout.fragment_product_details, container, false);

        textView_productName = (TextView)view_product.findViewById(R.id.productName);
        textView_productDescription=(TextView)view_product.findViewById(R.id.productDescription);
        imageView_product = (ImageView)view_product.findViewById(R.id.image_product);
        textView_productPrice = (TextView)view_product.findViewById(R.id.productPrice);

        product_name = this.getArguments().getString("ProductName");
        image_url = this.getArguments().getString("ImageUrl");
        product_price = this.getArguments().getString("ProductPrice");
        product_description = this.getArguments().getString("ProductDescription");

        textView_productName.setText(product_name);
        textView_productDescription.setText(product_description);
        textView_productPrice.setText(product_price);
        Picasso.with(context).load(image_url).into(imageView_product);



        return view_product;
    }

}
