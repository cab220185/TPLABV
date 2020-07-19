package com.example.tplabv;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;


public class Lista extends AppCompatActivity implements MyOnItemClick ,Handler.Callback , SearchView.OnQueryTextListener {

    private Handler handler;
    private MyAdapter adapter;
    static List<Similar> similares;
    private List<Similar> similaresaux;
    static Lista activitylista;
    static Activity lista;
    WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (MainActivity.tema_Claro) {
            setTheme(android.R.style.Theme_Light);
        } else {
            setTheme(android.R.style.Theme_Black);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        webView= (WebView) findViewById(R.id.mWebViewyoutube);

        handler = new Handler(this);
        similares = new ArrayList<Similar>();
        String url = super.getIntent().getExtras().get("url").toString();

        Hilo hilo = new Hilo(handler, url, true, null, "GET");
        hilo.start();

        activitylista = this;
        RecyclerView list = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        adapter = new MyAdapter(similares, this);
        list.setAdapter(adapter);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onItemClick(int position , View v) {

        if (v.getId() ==findViewById(R.id.linkyoutube).getId()){

            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + this.similares.get(position).getyID()));
            super.startActivity(appIntent);

        }else if(v.getId() ==findViewById(R.id.txttease).getId()) {

            Intent intent = new Intent(this, PaginaBrowser.class);
            intent.putExtra("urlwikipedia",this.similares.get(position).getwUrl());
            super.startActivity(intent);

        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.campo_buscar);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(this);

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        ImageView searchIcon = (ImageView)searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_filter_list_black_24dp);
        searchView.setQueryHint("Filter");

        return true;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

            if (msg.arg1 == 1) {

            similares.clear();
            similaresaux = new ArrayList<Similar>();
            similaresaux.addAll((List<Similar>) msg.obj);

           for (Similar s :similaresaux){

                if (s.getyID() =="null" ){

                }else {

                    this.similares.add(s);
                }

            }

            for (int i = 0; i< similares.size(); i++) {

             Hilo hiloimagen = new Hilo(handler, similares.get(i).getImagenlink(),false ,i,null);
             hiloimagen.start();
            }

            this.adapter.notifyDataSetChanged();

            }else if(msg.arg1 ==2) {

                 similares.get(msg.arg2).setImagen((byte[]) msg.obj);

                this.adapter.notifyDataSetChanged();
            }
            return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
         if(s.length()> 3) {
             adapter.getFilter().filter(s);

         }else {
             adapter.getFilter().filter("") ;

         }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
       if (s.length()> 3) {
           adapter.getFilter().filter(s);

       }else {
           adapter.getFilter().filter("") ;

       }
      return false;
    }

}