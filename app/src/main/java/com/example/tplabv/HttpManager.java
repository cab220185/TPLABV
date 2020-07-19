package com.example.tplabv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {

    public String consultarSimilares(String urlString) {

        try {

            URL url = new URL(java.net.URLDecoder.decode(urlString, "UTF-8"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            int response = urlConnection.getResponseCode();
            String respuesta = urlConnection.getResponseMessage();
            if (response == 200) {

                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream bais = new ByteArrayOutputStream();

                byte[] Buffer = new byte[1024];
                int cantidadbyte = 0;
                while ((cantidadbyte = is.read(Buffer)) != -1) {
                    bais.write(Buffer, 0, cantidadbyte);
                }

                is.close();

                return bais.toString();


            } else {

                throw new IOException("Error en el servidor");
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;

    }

    public byte[] consutarImagen(String urlString) {

        try {


            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int response = urlConnection.getResponseCode();
            if (response == 200) {
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream bais = new ByteArrayOutputStream();

                byte[] Buffer = new byte[1024];
                int cantidadbyte = 0;
                while ((cantidadbyte = is.read(Buffer)) != -1) {
                    bais.write(Buffer, 0, cantidadbyte);
                }

                is.close();

                return bais.toByteArray();


            } else {
                throw new IOException("Error en el servidor");
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;

    }

}
