package com.example.tema7_1_firebaserd_java;

public class Missatge{
        public String nom;
        public String contingut;

        public Missatge(){
        }

        public Missatge(String nom,String cont){
            this.nom=nom;
            this.contingut=cont;
        }

        public String getNom(){
            return this.nom;
        }
        public String getContingut(){
            return this.contingut;
        }
        public void setNom(String nom){
            this.nom=nom;
        }
        public void setContingut(String contingut){
            this.contingut=contingut;
        }
    }