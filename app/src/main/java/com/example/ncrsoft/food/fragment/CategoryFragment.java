package com.example.ncrsoft.food.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ncrsoft.food.R;
import com.example.ncrsoft.food.activity.Electronics;
import com.example.ncrsoft.food.activity.Men;
import com.example.ncrsoft.food.adapter.CategoryAdapter;
import com.example.ncrsoft.food.adapter.ExpandableListAdapter;
import com.example.ncrsoft.food.adapter.GetDataAdapter;
import com.example.ncrsoft.food.adapter.RecyclerViewAdapter;
import com.example.ncrsoft.food.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    public View rootview;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<GetDataAdapter> GetDataAdapter1;
    String CATEGORY_NAME = "CategoryName";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;

   CategoryAdapter recyclerViewadapter;

    ProgressBar progressBar;

    private String urlJsonArry = "http://api.zopac.in/api/category";
    private static String TAG = CategoryFragment.class.getSimpleName();
    private ProgressDialog pDialog;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview =  inflater.inflate(R.layout.fragment_home, container, false);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView1);

        progressBar = (ProgressBar)rootview.findViewById(R.id.progressBar1);


        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        makeJsonArrayRequest();

        return rootview;
    }

    private void makeJsonArrayRequest() {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
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
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject person = null;
            try {
                person = array.getJSONObject(i);


                GetDataAdapter2.setCategoryName(person.getString(CATEGORY_NAME));
            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new CategoryAdapter(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}


