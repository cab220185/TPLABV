package com.example.tplabv;

public class Similar {

    private String name ;
    private String type ;
    private String wTeaser ;
    private String wUrl ;
    private String yUrl ;
    private String yID ;
    private byte[]imagen ;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen; }

    public String getImagenlink() {
        return imagenlink;
    }

    public void setImagenlink(String imagenlink) {
        this.imagenlink = imagenlink;
    }

    private String imagenlink ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getwTeaser() {
        return wTeaser;
    }

    public void setwTeaser(String wTeaser) {
        this.wTeaser = wTeaser;
    }

    public String getwUrl() {
        return wUrl;
    }

    public void setwUrl(String wUrl) {
        this.wUrl = wUrl;
    }

    public String getyUrl() {
        return yUrl;
    }

    public void setyUrl(String yUrl) {
        this.yUrl = yUrl;
    }

    public String getyID() {
        return yID;
    }

    public void setyID(String yID) {
        this.yID = yID;
    }



   public Similar (){}


}



