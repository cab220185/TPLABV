package com.example.tplabv;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Parser {

    Similar similar;
    String jsonsimilares;
    ArrayList<Similar> listasmimilares;

    public Parser(String jsonsimilares)
    {

        this.jsonsimilares =jsonsimilares ;
    }

    public ArrayList<Similar> ParserSimilares()
    {
        listasmimilares = new ArrayList<Similar> ();



        try {
            JSONObject  jsonObject = new JSONObject(jsonsimilares.toString());

            JSONObject jsonsimilar = jsonObject.getJSONObject("Similar");

            JSONArray info = jsonsimilar.getJSONArray("Info");

            for (int i = 0; i < info.length(); i++) {

                JSONObject detalleSimilar = info.getJSONObject(i);
                similar = new Similar() ;


                similar.setName((detalleSimilar.getString("Name") != null )?detalleSimilar.getString("Name") : null);
                similar.setType((detalleSimilar.getString("Type") != null )?detalleSimilar.getString("Type") : null);
                similar.setwTeaser((detalleSimilar.getString("wTeaser") != null )?detalleSimilar.getString("wTeaser") : null); ;
                similar.setwUrl((detalleSimilar.getString("wUrl") != null ) ?detalleSimilar.getString("wUrl") : null ); ;
                similar.setyUrl((detalleSimilar.getString("yUrl") != null ) ?detalleSimilar.getString("yUrl") : null );
                similar.setyID((detalleSimilar.getString("yID") != null ) ?detalleSimilar.getString("yID") : null );
                similar.setImagenlink((detalleSimilar.getString("yID") != null ) ? "https://img.youtube.com/vi/"+detalleSimilar.getString("yID") +"/default.jpg": null);

                listasmimilares.add(similar);


            }

        }catch  (Exception e){

            e.printStackTrace();

        }
        try {

            JSONObject  jsonObject = new JSONObject(jsonsimilares.toString());

            JSONObject jsonsimilar = jsonObject.getJSONObject("Similar");

            JSONArray results = jsonsimilar.getJSONArray("Results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject detalleSimilar = results.getJSONObject(i);
                similar = new Similar() ;

                 similar.setName((detalleSimilar.getString("Name") != null )?detalleSimilar.getString("Name") : null);
                 similar.setType((detalleSimilar.getString("Type") != null )?detalleSimilar.getString("Type") : null);
                 similar.setwTeaser((detalleSimilar.getString("wTeaser") != null )?detalleSimilar.getString("wTeaser") : null); ;
                 similar.setwUrl((detalleSimilar.getString("wUrl") != null ) ?detalleSimilar.getString("wUrl") : null ); ;
                 similar.setyUrl((detalleSimilar.getString("yUrl") != null ) ?detalleSimilar.getString("yUrl") : null );
                 similar.setyID((detalleSimilar.getString("yID") != null ) ?detalleSimilar.getString("yID") : null );
                 similar.setImagenlink((detalleSimilar.getString("yID") != null ) ? "https://img.youtube.com/vi/"+detalleSimilar.getString("yID") +"/default.jpg": null);

                listasmimilares.add(similar);

                }

        }catch  (Exception e){

            e.printStackTrace();

        }

        return listasmimilares;

    }

}
