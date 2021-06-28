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

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class WorldActivity extends AppCompatActivity {

    private TextView head,data1, data2, data3, data4, data5,data6,data7,data8,data9,data10;
    private RequestQueue mQueue;
    ProgressBar progBar;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        getSupportActionBar().setTitle("World");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        head=findViewById(R.id.head);
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

        progBar = findViewById(R.id.progBar);
        barChart=findViewById(R.id.barChart);


        mQueue = Volley.newRequestQueue(this);
        stateParse();
    }

    private void stateParse() {

        String url = "https://disease.sh/v3/covid-19/all";

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
                            int countries=jsonObject.getInt("affectedCountries");



                            double recovRate=(totalRecovered*100)/totalcase;
                            double deathRate=(totaldeaths*100)/totalcase;

                            head.setText("Total Affected Countries: "+countries);
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

                            barChart.addBar(new BarModel("Cases",Integer.parseInt(String.valueOf(totalcase)), Color.parseColor("#000000")));
                            barChart.addBar(new BarModel("Recovered",Integer.parseInt(String.valueOf(totalRecovered)), Color.parseColor("#60DA03")));
                            barChart.addBar(new BarModel("Deaths",Integer.parseInt(String.valueOf(totaldeaths)), Color.parseColor("#DA032A")));
                            barChart.addBar(new BarModel("Active",Integer.parseInt(String.valueOf(active)), Color.parseColor("#FF9800")));
                            barChart.startAnimation();


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
