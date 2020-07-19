package com.example.tplabv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,  DialogInterface.OnClickListener  {

    CheckBox pelicula ;
    CheckBox musica ;
    CheckBox libros ;
    Button   btnbuscar ;
    EditText edtbuscar ;
    View mView ;
    static String apikeytd ;
    static Boolean tema_Claro = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CargarPreferenciasTema();

        if (tema_Claro){
            setTheme(android.R.style.Theme_Light ); }
        else {
            setTheme(android.R.style.Theme_Black ); }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        pelicula = (CheckBox)findViewById(R.id.chkpeliculas);
        pelicula.setOnClickListener(this);
        musica = (CheckBox)findViewById(R.id.chkmusica);
        musica.setOnClickListener(this);
        libros = (CheckBox)findViewById(R.id.chkllibros);
        libros.setOnClickListener(this);
        edtbuscar =(EditText)findViewById(R.id.edtbuscar);
        btnbuscar = (Button)findViewById(R.id.btnbuscar);
        btnbuscar.setOnClickListener(this);
        CargarPreferenciasBusqueda();
        apikeytd = CargarPreferenciasApi();


    }



    private String GenerarUrl(String busqueda) {

        HashMap<String,Boolean>tiporesultado =new HashMap<>();
        tiporesultado.put("movies",pelicula.isChecked());
        tiporesultado.put("books",libros.isChecked());
        tiporesultado.put("music",musica.isChecked());

        StringBuilder tipobusqueda = new StringBuilder();
        boolean first = true;

        for (Map.Entry me : tiporesultado.entrySet()) {

            if ((Boolean)me.getValue()==true ) {
                if (first) {
                    first = false;
                } else {
                    tipobusqueda.append(',');
                }
                tipobusqueda.append(String.valueOf(me.getKey()));
            }
        }

        String parametrotype =(tipobusqueda.length() >1 )?String.valueOf(tipobusqueda):"movies,music,books";


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("tastedive.com")
                .appendPath("api")
                .appendPath("similar")
                .appendQueryParameter("q", busqueda)
                .appendQueryParameter("type", parametrotype )
                .appendQueryParameter("k",apikeytd)
                .appendQueryParameter("verbose", "1");
        String myUrl = builder.build().toString();

        return myUrl  ;

    }

    private  void GuardarPreferenciasBusqueda ( ){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("pelicula", pelicula.isChecked());
        editor.putBoolean("musica", musica.isChecked());
        editor.putBoolean("libros", libros.isChecked());
        editor.apply();
    }
    private void CargarPreferenciasBusqueda (){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        pelicula.setChecked( preferences.getBoolean("pelicula", true ));
        musica.setChecked( preferences.getBoolean("musica", true ));
        libros.setChecked( preferences.getBoolean("libros", true ));
    }
    private  void GuardarPreferenciasTema ( ){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("temaclaro", tema_Claro);
        editor.apply();
    }
    private void CargarPreferenciasTema (){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        tema_Claro = preferences.getBoolean("temaclaro", true);
    }
    private  void GuardarPreferenciasApi (String apikeytd){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("apikey", apikeytd);
        editor.apply();
    }
    private String CargarPreferenciasApi (){
        SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        apikeytd = preferences.getString("apikey", "377068-TPLABV-S4HU5AR1");
        return apikeytd ;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this, (tema_Claro) ? R.style.MyAlertDialogThemeWhite : R.style.MyAlertDialogThemeBlack);

            mView = getLayoutInflater().inflate(R.layout.dialog_tema_layout, null);

            mBuilder.setView(mView);
            mBuilder.setPositiveButton("Accept", MainActivity.this);
            mBuilder.setNegativeButton("Cancel", MainActivity.this);

            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }
         else if (id == R.id.ApiKeyTasteDive){

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this, (tema_Claro) ? R.style.MyAlertDialogThemeWhite : R.style.MyAlertDialogThemeBlack);

            mView = getLayoutInflater().inflate(R.layout.dialog_apikeytastedive, null);
            final EditText edapikeytastedive = (EditText) mView.findViewById(R.id.edtapykeytastedive);
            Button btnGuardar = (Button) mView.findViewById(R.id.btnGuardarapikeytastedive);

            mBuilder.setView(mView);

            final AlertDialog dialog = mBuilder.create();
            dialog.show();

            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!edapikeytastedive.getText().toString().isEmpty()){

                        GuardarPreferenciasApi(edapikeytastedive.getText().toString());
                        apikeytd =  CargarPreferenciasApi();
                        dialog.dismiss();
                    }else{ }
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        GuardarPreferenciasBusqueda();

        if(v.getId()==R.id.btnbuscar) {
            Intent intent = new Intent(this, Lista.class);
            String tituloabuscar = this.edtbuscar.getText().toString();
            String tituloabuscartrim = tituloabuscar.trim();
            String busquedafinal = tituloabuscartrim.replace(' ', '+');
            String urlarmada = null;
            urlarmada = GenerarUrl(busquedafinal);
            intent.putExtra("url", urlarmada);
            super.startActivity(intent);

        }

    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        final RadioGroup rgTemaAplicacion = (RadioGroup) mView.findViewById(R.id.radiogroup_tema);

        int elegido = rgTemaAplicacion.getCheckedRadioButtonId();

        if (which == AlertDialog.BUTTON_POSITIVE) {

            if (elegido == R.id.radiobutton_claro  ){

                tema_Claro = true ;
                GuardarPreferenciasTema();
                recreate();

            }else if (elegido == R.id.radiobutton_oscuro ) {

                tema_Claro = false;
                GuardarPreferenciasTema();
                recreate();

            }

        }

    }
}