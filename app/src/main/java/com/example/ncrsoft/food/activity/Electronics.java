package com.example.ncrsoft.food.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ncrsoft.food.R;

import com.example.ncrsoft.food.adapter.ElectronicsAdapter;
import com.example.ncrsoft.food.adapter.GetElectronicsData;
import com.example.ncrsoft.food.adapter.RecyclerViewAdapter;
import com.example.ncrsoft.food.app.AppController;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Electronics extends AppCompatActivity {

    String product_id;
    Bundle bundle;
    //TextView textView;

    //private List<Album> albumList;
    List<GetElectronicsData> getElectronicsData1;

    RecyclerView recyclerView;

    String subcat_id = "SubcategoryId";
    String subcat_name = "SubcategoryName";

    RecyclerView.LayoutManager recyclerViewlayoutManagerEC;

    RecyclerView.Adapter recyclerViewadapterEC;

    ProgressBar progressBar;

    private String urlJsonArry = "http://api.zopac.in/api/Category?parentCategoryId=";
    private static String TAG = Electronics.class.getSimpleName();
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);

        getElectronicsData1 = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_common);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar_common);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManagerEC = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManagerEC);


        bundle = getIntent().getExtras();
        product_id = bundle.getString("id");

        makeJsonArrayRequest(product_id);



        //textView.setText(product_id);
    }

    private void makeJsonArrayRequest(String aa) {
       String url =urlJsonArry+aa;
        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                       /*try {
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);
                                String subId = person.getString("SubcategoryId");
                                String subName = person.getString("SubcategoryName");

                                jsonResponse += "Sub ID: " + subId + "\n\n";
                                jsonResponse += "Sub Name: " + subName + "\n\n";
                            }

                            txtResponse.setText(jsonResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }*/
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

            GetElectronicsData getElectronicsData2 = new GetElectronicsData();

            JSONObject person = null;
            try {
                person = array.getJSONObject(i);

                // GetDataAdapter2.setStatus(person.getString(STATUS));
                getElectronicsData2.setImageUrl(person.getString(subcat_id));
                getElectronicsData2.setProductName(person.getString(subcat_name));
            } catch (JSONException e) {

                e.printStackTrace();
            }
            getElectronicsData1.add(getElectronicsData2);
        }

        recyclerViewadapterEC = new ElectronicsAdapter(getElectronicsData1, this);

        recyclerView.setAdapter(recyclerViewadapterEC);

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


