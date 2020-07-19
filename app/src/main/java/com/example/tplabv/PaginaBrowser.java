package com.example.tplabv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaginaBrowser extends AppCompatActivity implements View.OnClickListener {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (MainActivity.tema_Claro ) {
            setTheme(android.R.style.Theme_Light ); }
        else {
            setTheme(android.R.style.Theme_Black ); }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculapagina);

        ActionBar toolbar =  getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

        webView = (WebView)findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(super.getIntent().getExtras().get("urlwikipedia").toString());

        FloatingActionButton btnflotante = (FloatingActionButton) findViewById(R.id.btnflotante) ;
        btnflotante.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String urlCompartir = webView.getUrl().toString();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Take a look");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, urlCompartir);
        startActivity(Intent.createChooser(sharingIntent, "Share :"));

    }
}
