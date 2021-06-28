package com.example.proapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryActivity extends AppCompatActivity {

    private TextView data1, data2, data3, data4, data5, data6, data7, data8,data9,data10,head;
    private RequestQueue mQueue;
    ProgressBar progBar;
    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        getSupportActionBar().setTitle("INDIA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data1 = findViewById(R.id.data1);
        data2 = findViewById(R.id.data2);
        data3 = findViewById(R.id.data3);
        data4 = findViewById(R.id.data4);
        data5 = findViewById(R.id.data5);
        data6 = findViewById(R.id.data6);
        data7 = findViewById(R.id.data7);
        data8 = findViewById(R.id.data8);
        data9 = findViewById(R.id.data9);
        data10 = findViewById(R.id.data10);
//        head=findViewById(R.id.head);
        piechart=findViewById(R.id.piechart);

        progBar=findViewById(R.id.progBar);


        mQueue = Volley.newRequestQueue(this);

        covidParse();

    }

    private void covidParse() {

        String url = "https://disease.sh/v3/covid-19/countries/India";

        progBar.setVisibility(View.VISIBLE);


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            int totalcase=jsonObject.getInt("cases");
                            int todaycase=jsonObject.getInt("todayCases");
                            int totaldeaths=jsonObject.getInt("deaths");
                            int todaydeaths=jsonObject.getInt("todayDeaths");
                            int totalRecovered=jsonObject.getInt("recovered");
                            int todayrecovered=jsonObject.getInt("todayRecovered");
                            int active=jsonObject.getInt("active");
                            int critical=jsonObject.getInt("critical");


                            double recovRate=(totalRecovered*100)/totalcase;
                            double deathRate=(totaldeaths*100)/totalcase;

//                            head.setText(jsonObject.getString("country"));
                            data1.setText("Total Covid-19 Cases till date: "+totalcase);
                            data2.setText("New cases today: "+todaycase);
                            data3.setText("Tota deceased cases: "+totaldeaths);
                            data4.setText("Deceased cases today: "+todaydeaths);
                            data5.setText("Total recovered: "+totalRecovered);
                            data6.setText("Recovered today: "+todayrecovered);
                            data7.setText("Active cases: "+active);
                            data8.setText("Critical cases: "+critical);
                            data9.setText("Recovery Rate: "+recovRate+"%");
                            data10.setText("Death Rate: "+deathRate+"%");

                            piechart.addPieSlice(new PieModel("Cases",Integer.parseInt(String.valueOf(totalcase)), Color.parseColor("#000000")));
                            piechart.addPieSlice(new PieModel("Recovered",Integer.parseInt(String.valueOf(totalRecovered)),Color.parseColor("#145A32")));
                            piechart.addPieSlice(new PieModel("Deaths",Integer.parseInt(String.valueOf(totaldeaths)),Color.parseColor("#DA032A")));
                            piechart.addPieSlice(new PieModel("Active",Integer.parseInt(String.valueOf(active)),Color.parseColor("#FF9800")));
                            piechart.startAnimation();

                            progBar.setVisibility(View.GONE);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progBar.setVisibility(View.GONE);
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progBar.setVisibility(View.GONE);
            }
        });

        mQueue.add(request);
    }
}
