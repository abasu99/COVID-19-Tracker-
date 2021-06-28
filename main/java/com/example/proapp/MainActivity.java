package com.example.proapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StateAdapter mStateAdapter;
    private ArrayList<StateList> mStateList;
    private RequestQueue mRequestQueue;
    private ProgressBar progBar;
    ViewFlipper viewFlipper;
//    TextView data6,data7;
//    BarChart barChart;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.country:{
                startActivity(new Intent(MainActivity.this,CountryActivity.class));
                break;
            }
            case R.id.world:{
                startActivity(new Intent(MainActivity.this,WorldActivity.class));
                break;
            }
            case R.id.nos:{
                startActivity(new Intent(MainActivity.this,pdfViewer.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[]={R.drawable.main,R.drawable.one,R.drawable.two,R.drawable.three,
                R.drawable.four,R.drawable.five,R.drawable.six};
        viewFlipper=findViewById(R.id.viewFlipper);

//        data6=findViewById(R.id.data6);
//        data7=findViewById(R.id.data7);
        getSupportActionBar().setTitle("States");

        mRecyclerView=findViewById(R.id.recyclerView);
        progBar=findViewById(R.id.progBar);
//        barChart=findViewById(R.id.barChart);

        //The below code is for ViewFlipper
        for (int i=0;i<images.length;i++){
            flipperImages(images[i]);
        }

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStateList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void flipperImages(int image) {

        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(9000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }


    private void parseJSON() {

        String url = "https://api.covid19india.org/data.json";

        progBar.setVisibility(View.VISIBLE);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray= response.getJSONArray("statewise");
                            for (int i = 1; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String head=object.getString("state");
                                String cases=object.getString("confirmed");
                                String recovered=object.getString("recovered");
                                String deaths=object.getString("deaths");
                                String active=object.getString("active");
                                String time=object.getString("lastupdatedtime");

//                                double recovRate= Integer.parseInt(recovered)*100 /Integer.parseInt(cases);
//                                double deathRate=Integer.parseInt(deaths)*100 /Integer.parseInt(cases);
//
//                                data6.setText("Recovery Rate: "+recovRate+"%");
//                                data7.setText("Death Rate: "+deathRate+"%");



                                mStateList.add(new StateList(head,cases,recovered,deaths,active,time));

//                                barChart.addBar(new BarModel("Cases",Integer.parseInt(String.valueOf(cases)), Color.parseColor("#000000")));
//                                barChart.addBar(new BarModel("Recovered",Integer.parseInt(String.valueOf(recovered)), Color.parseColor("#60DA03")));
//                                barChart.addBar(new BarModel("Deaths",Integer.parseInt(String.valueOf(deaths)), Color.parseColor("#DA032A")));
//                                barChart.addBar(new BarModel("Active",Integer.parseInt(String.valueOf(active)), Color.parseColor("#FF9800")));
//                                barChart.startAnimation();

                            }
                            mStateAdapter=new StateAdapter(MainActivity.this,mStateList);
                            mRecyclerView.setAdapter(mStateAdapter);

                            progBar.setVisibility(View.GONE);
                        }
                        catch (JSONException e) {
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
        mRequestQueue.add(request);
    }
}
