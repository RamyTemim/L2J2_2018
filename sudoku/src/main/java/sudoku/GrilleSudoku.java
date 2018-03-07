package sudoku;

import lombok.Data;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Data
public class GrilleSudoku {

    //Setter
    public void setTab(int tab[][]) {
        this.tab = tab;
    }

    //Getter
    public int getTab(int i, int j) {
        return tab[i][j];
    }

    //Déclaration du tableau = la grille
    private int[][] tab = new int[9][9];


    /****************** Vérifications ***********************/

    //Vérifie si déjà sur la ligne
    public boolean existLigne(int i, int nb) {
        for (int j = 0; j < 9; j++) {
            if (tab[i][j] == nb) {
                return true;
            }
        }
        return false;
    }

    //Vérifie si déjà sur la colonne
    public boolean existColonne(int j, int nb) {
        for (int i = 0; i < 9; i++) {
            if (tab[i][j] == nb) {
                return true;
            }
        }
        return false;
    }

    //Vérifie si déjà dans le bloc
    public boolean existBloc(int i, int j, int nb) {
        int k = i - (i % 3), l = j - (j % 3);
        for (i = k; i < k + 3; i++) {
            for (j = l; j < l + 3; j++) {
                if (tab[i][j] == nb)
                    return true;
            }
        }
        return false;
    }

    //Vérifie que le nb n'est ni dans le bloc, ni sur la colonne ni sur la ligne
    public boolean checkNumber(int i, int j, int nb) {
        int n = nb;
        return (existLigne(i, n) || existColonne(j, n) || existBloc(i, j, n));
    }


    /****************** Création & Affichage ***********************/


    //Génère un nombre aléatoire entre 1 et 9
    public int generateNb() {
        return (int) (Math.random() * 9) + 1;
    }

    public void generateNbRamdom() {
        int h = 20;
        int i,j,num;
        for (int l=0;l<=h;l++){
            i = (int) (Math.random() * 8);
            j = (int) (Math.random() * 8);

            num = generateNb();
            while(checkNumber(i,j,num)) {
                num =generateNb();
            }

            tab[i][j]=num;
        }
    }


    //Génère une ligne de la grille
    public void generateLigne(int m) {
        int num;

        for (int j = 0; j < 9; j++) {

            if (tab[m][j] != 0) continue;

            num = generateNb();

            while (checkNumber(m, j, num)) {
                num = generateNb();
            }

            tab[m][j] = num;
        }

    }

    //Génère une colonne de la grille
    public void generateColon(int n) {
        int num2;

        for (int i = 0; i < 9; i++) {
            if (tab[i][n] != 0) continue;

            num2 = generateNb();

            while (checkNumber(i, n, num2)) {
                num2 = generateNb();
            }

            tab[i][n] = num2;
        }
    }

    //Génère un bloc de la grille
    public void generateBloc(int i, int j) {
        int num;
        int k = i - (i % 3), l = j - (j % 3);
        for (i = k; i < k + 3; i++) {
            for (j = l; j < l + 3; j++) {
                if (tab[i][j] != 0) continue;

                num = generateNb();

                while (checkNumber(i, j, num)) {
                    num = generateNb();
                }

                tab[i][j] = num;
            }
        }

    }



    //Affiche la grille
    public void afficherGrille() {
        for (int i = 0; i < 3; i++) {
            System.out.println(tab[i][0] + " " + tab[i][1] + " " + tab[i][2] + "   " + tab[i][3] + " " + tab[i][4] + " " + tab[i][5] + "   " + tab[i][6] + " " + tab[i][7] + " " + tab[i][8]);
        }
        System.out.println(" ");

        for (int i = 3; i < 6; i++) {
            System.out.println(tab[i][0] + " " + tab[i][1] + " " + tab[i][2] + "   " + tab[i][3] + " " + tab[i][4] + " " + tab[i][5] + "   " + tab[i][6] + " " + tab[i][7] + " " + tab[i][8]);
        }
        System.out.println(" ");

        for (int i = 6; i < 9; i++) {
            System.out.println(getTab(i, 0) + " " + getTab(i, 1) + " " + getTab(i, 2) + "   " + getTab(i, 3) + " " + getTab(i, 4) + " " + getTab(i, 5) + "   " + getTab(i, 6) + " " + getTab(i, 7) + " " + getTab(i, 8));
        }

    }




}
