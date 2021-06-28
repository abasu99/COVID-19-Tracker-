package com.example.proapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class pdfViewer extends AppCompatActivity {

    private PDFView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        getSupportActionBar().setTitle("Helpline Nos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdf=findViewById(R.id.pdf);

        pdf.fromAsset("coronvavirushelplinenumber.pdf").load();
    }
}
