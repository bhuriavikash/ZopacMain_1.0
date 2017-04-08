package com.example.ncrsoft.food.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ncrsoft.food.R;
import com.example.ncrsoft.food.activity.Electronics;
import com.example.ncrsoft.food.adapter.ElectronicsAdapter;
import com.example.ncrsoft.food.adapter.GetElectronicsData;
import com.example.ncrsoft.food.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {

    public View view_sub;
    String product_id;

    List<GetElectronicsData> getElectronicsData1;
    RecyclerView recyclerView;

    String image_url = "ImageUrl";
    String product_name = "ProductName";
    String product_price = "ProductPrice";
    String product_description = "ProductDescription";
    RecyclerView.LayoutManager recyclerViewlayoutManagerEC;
    GridLayoutManager gridLayoutManager;
    ElectronicsAdapter recyclerViewadapterEC;

    ProgressBar progressBar;
    private String urlJsonArry = "http://api.zopac.in/api/Search?categoryId=";
    private static String TAG = SubCategoryFragment.class.getSimpleName();
    public ProgressDialog pDialog;

    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view_sub = inflater.inflate(R.layout.fragment_sub_category, container, false);
        getElectronicsData1 = new ArrayList<>();

        recyclerView = (RecyclerView)view_sub.findViewById(R.id.recycler_view_common);

        progressBar = (ProgressBar)view_sub.findViewById(R.id.progress_bar_common);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);

        //recyclerViewlayoutManagerEC = new LinearLayoutManager(getActivity());
         gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


        product_id = this.getArguments().getString("index");

        makeJsonArrayRequest(product_id);
        return view_sub;
    }

    private void makeJsonArrayRequest(String aa) {
        String url =urlJsonArry+aa;
        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        progressBar.setVisibility(View.GONE);

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);

                        hidepDialog();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetElectronicsData getElectronicsData2 = new GetElectronicsData();

            JSONObject person = null;
            try {
                person = array.getJSONObject(i);

                getElectronicsData2.setProductDescription(person.getString(product_description));
                getElectronicsData2.setProductPrice(person.getString(product_price));
                getElectronicsData2.setImageUrl(person.getString(image_url));
                getElectronicsData2.setProductName(person.getString(product_name));
            } catch (JSONException e) {

                e.printStackTrace();
            }
            getElectronicsData1.add(getElectronicsData2);
        }

        recyclerViewadapterEC = new ElectronicsAdapter(getElectronicsData1, getActivity());

        recyclerView.setAdapter(recyclerViewadapterEC);

    }
    public void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
