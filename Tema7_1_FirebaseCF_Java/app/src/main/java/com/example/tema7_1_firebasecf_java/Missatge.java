package com.example.tema7_1_firebasecf_java;

public class Missatge{
    public String nom;
    public long data;
    public String contingut;

    public Missatge(){
    }

    public Missatge(String nom,String cont){
        this.nom=nom;
        this.contingut=cont;
    }

    public Missatge(String nom, long data, String contingut) {
        super();
        this.nom = nom;
        this.data = data;
        this.contingut = contingut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getContingut() {
        return contingut;
    }

    public void setContingut(String contingut) {
        this.contingut = contingut;
    }

}

