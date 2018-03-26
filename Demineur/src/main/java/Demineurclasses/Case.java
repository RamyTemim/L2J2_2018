package Demineurclasses;

public class Case {
    private boolean DrapeauSurCase = false; //True si il y a un drapeau sur la case;
    private Boolean DecouvertCase = false; // True si la case est découverte
    private Boolean MineCase = false; //True si la case est minée
    private int NbMineProximite; //nombre de mine a proximité
public Case(){
    this.DrapeauSurCase = DrapeauSurCase;
    this.DecouvertCase = DecouvertCase;
    this.MineCase = MineCase;
    this.NbMineProximite = NbMineProximite;
}
    public Case(Boolean mineCase) {
        MineCase = mineCase;
    }

    //getter retourne true si la case est découverte false sinon
    public boolean get_decouvert() {
        return DecouvertCase;
    }

    //méthode permettant de decouvrir si une case contient un drapeau
    public boolean decouvrir() {
        if (get_drapeau() == false) {
            DecouvertCase = true;
        }
        return DecouvertCase;
    }

    //getter qui permet de retourner true si un drapeau est mis sur une case false sinon
    public boolean get_drapeau() {
        return DrapeauSurCase;
    }

    //methode qui permet de mettre ou d'enlever un drapeau sur une case
    public boolean set_drapeau(boolean choix) {
        if (get_decouvert() == false) {
            DrapeauSurCase = choix;
            return DrapeauSurCase;
        }
        return DrapeauSurCase;
    }

    //getter , true si la case est minée false sinon
    public boolean get_mine() {
        return MineCase;

    }

    //getter , retourne le nombre de mines a proximité
    public int get_NbMineProximite() {

        if (NbMineProximite > 8) {
            System.out.println("erreur on ne peut pas avoir plus 8 mines autour d'une case");
        } else {
            System.out.println(+NbMineProximite);
        }


        return NbMineProximite;
    }

    public void set_NbMineProximite(int NbMineproximite) {
        NbMineProximite = NbMineProximite;

    }

    //méthode qui affiche une case
    //retourne une Case Minée si MineCase vaut true


    @Override
    public String toString() {
        if (DrapeauSurCase) return "!";
        else if (DecouvertCase != false) return "?";
        else if (get_mine() == true) return "X";
        else if(NbMineProximite > 0)return ""+NbMineProximite;
        else return "." ;
}
}