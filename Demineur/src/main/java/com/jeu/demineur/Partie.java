package com.jeu.demineur;

public class Partie {
    private Demineur demineur = new Demineur();
    int temps = 0 ;
    boolean gagne = false ;
    int niveau ;

    public Partie(Demineur d) {

        this.demineur = d;
    }

    public int getTemps() {

        return temps;
    }
    public void MiseAjourTemps(){

        temps++;
    }

    public int getNiveau() {

        return niveau;
    }


    public void setNiveau(int n) {

        niveau = n;
    }

}
