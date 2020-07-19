package com.example.tplabv;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

public class Hilo extends Thread {

    Handler handler ;
    String  url ;
    Boolean texto ;
    Integer posicion ;
    String peticion ;
    List<Similar> listasimilares;

    public Hilo (Handler handler , String url, Boolean texto, Integer posicion ,String peticion){
        this.handler = handler ;
        this.url = url ;
        this.texto= texto;
        this.posicion = posicion;
        this.peticion = peticion ;

    }

    @Override
    public void run (){

        if(texto &&  peticion == "GET"){
            HttpManager manager = new HttpManager();
            String respuesta =  manager.consultarSimilares(this.url);

            listasimilares = new ArrayList<Similar>();
            listasimilares.clear();
            Parser parser = new Parser(respuesta) ;

            listasimilares =   parser.ParserSimilares();


            Message message = new Message();
            message.obj = listasimilares;
            message.arg1=1;
            this.handler.sendMessage(message);}


        else  {

            HttpManager manager = new HttpManager();
            byte[] imagen =  manager.consutarImagen(this.url);

            Message message = new Message();
            message.obj =  imagen ;
            message.arg1=2;
            message.arg2 = posicion ;
            this.handler.sendMessage(message);
        }

    }

}