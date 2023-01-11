package it.michaelsaccone.sudoku;

import javax.swing.*;
import java.util.Random;

public class Matrix {
    final static Random generator = new Random();
    int matrix[][];

    /**
     * crea una matrice 4x4 di interi
     */
    public Matrix() {
        matrix = new int[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                matrix[row][col] = 0;
            }
        }
    }

    /**
     * Inizializza la matrice mettendo a zero tutte le celle,
     * e poi selezionandone 9 a caso e popolando ciascuna con
     * un valore casuale scelto tra 1, 2 e 3
     */
    public void initializeMatrix() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                matrix[row][col] = 0;
            }
        }
        for (int n = 1; n < 10; n++) {
            int i;
            int j;
            do {
                i = generator.nextInt(4);
                j = generator.nextInt(4);
            } while (matrix[i][j] != 0);
            matrix[i][j] = generator.nextInt(3) + 1;
        }
    }

    /**
     * Esegui un azione (spostamento, fine):
     * L sposta a sinistra (left)
     * R sposta a sinistra (right)
     * U sposta a sinistra (up)
     * D sposta a sinistra (down)
     * L'azione modifica in modo opportuno la matrice matrix.
     * @param action il carattere che identifica l'azione (U,D,L,R)
     */
    public void performAction(String action) {
        boolean success[]=new boolean[4];
        int pos=0;
        switch (action) {
            case "L": //===============================================================
                // esegui gli spostamenti possibili
                for (int row = 0; row < 4; row++) {
                    success[row] = false;
                    for (int col = 1; col < 4; col++) {
                        // se vi è una cella vuota in posizione oppottuna effettua lo spostamento
                        if ((matrix[row][col - 1] == 0) && (matrix[row][col] != 0)) {
                            matrix[row][col - 1] = matrix[row][col];
                            matrix[row][col] = 0;
                            success[row] = true;
                        } else {
                            // se vi è una cella compatibile in posizione opportuna somma le celle
                            if (isCompatible(matrix[row][col - 1], matrix[row][col])) {
                                matrix[row][col - 1] = matrix[row][col] + matrix[row][col - 1];
                                matrix[row][col] = 0;
                                success[row] = true;
                            }
                        }
                    }
                }
                // se almeno uno spostamento è stato effettuato, genera
                // in modo casuale un nuovo valore
                //System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
                if (success[0] || success[1] || success[2] || success[3]) {
                    do {
                        pos = generator.nextInt(4);
                    } while (!success[pos]);
                    matrix[pos][3] = generator.nextInt(3) + 1;
                }
                break;
            case "R": //===============================================================
                for (int row = 0; row < 4; row++) {
                    success[row] = false;
                    for (int col = 2; col >= 0; col--) {
                        if ((matrix[row][col + 1] == 0) && (matrix[row][col] != 0)) {
                            matrix[row][col + 1] = matrix[row][col];
                            matrix[row][col] = 0;
                            success[row] = true;
                        } else {
                            if (isCompatible(matrix[row][col + 1], matrix[row][col])) {
                                matrix[row][col + 1] = matrix[row][col] + matrix[row][col + 1];
                                matrix[row][col] = 0;
                                success[row] = true;
                            }
                        }
                    }
                }
                //System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
                if (success[0] || success[1] || success[2] || success[3]) {
                    do {
                        pos = generator.nextInt(4);
                    } while (!success[pos]);
                    matrix[pos][0] = generator.nextInt(3) + 1;
                }
                break;
            case "U": //===============================================================
                for (int col = 0; col < 4; col++) {
                    success[col] = false;
                    for (int row = 1; row < 4; row++) {
                        if ((matrix[row - 1][col] == 0) && (matrix[row][col] != 0)) {
                            matrix[row - 1][col] = matrix[row][col];
                            matrix[row][col] = 0;
                            success[col] = true;
                        } else {
                            if (isCompatible(matrix[row - 1][col], matrix[row][col])) {
                                matrix[row - 1][col] = matrix[row][col] + matrix[row - 1][col];
                                matrix[row][col] = 0;
                                success[col] = true;
                            }
                        }
                    }
                }
                //System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
                if (success[0] || success[1] || success[2] || success[3]) {
                    do {
                        pos = generator.nextInt(4);
                    } while (!success[pos]);
                    matrix[3][pos] = generator.nextInt(3) + 1;
                }
                break;
            case "D": //===============================================================
                for (int col = 0; col < 4; col++) {
                    success[col] = false;
                    for (int row = 2; row >= 0; row--) {
                        if ((matrix[row + 1][col] == 0) && (matrix[row][col] != 0)) {
                            matrix[row + 1][col] = matrix[row][col];
                            matrix[row][col] = 0;
                            success[col] = true;
                        } else {
                            if (isCompatible(matrix[row + 1][col], matrix[row][col])) {
                                matrix[row + 1][col] = matrix[row][col] + matrix[row + 1][col];
                                matrix[row][col] = 0;
                                success[col] = true;
                            }
                        }
                    }
                }
                //System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
                if (success[0] || success[1] || success[2] || success[3]) {
                    do {
                        pos = generator.nextInt(4);
                    } while (!success[pos]);
                    matrix[0][pos] = generator.nextInt(3) + 1;
                }
                break;
        }
    }

    private boolean isCompatible(int a, int b) {
        if (a == 1 && b == 2) return true;
        if (a == 2 && b == 1) return true;
        if (a>2 && a == b) return true;
        return false;
    }

    /**
     * calcola il punteggio totale della matrice
     * @return
     */
    public int computeSum() {
        int sum=0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                sum+=matrix[row][col];
            }
        }
        return sum;
    }
    public String printMatrix() {
        StringBuffer sb = new StringBuffer("");
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                sb.append(matrix[row][col]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Main di test
     */
    public static void main(String[]a){
        Matrix m=new Matrix();
        m.initializeMatrix();
        System.out.println(m.printMatrix()+"\n----------------------");
        boolean repeat=true;
        do{
            Object[] options = {"END","Right", "Up", "Down", "Left"};
            int n = JOptionPane.showOptionDialog(null,
                    "Choose an option",
                    "Chooser",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[4]);
            System.out.println(n);
            switch (n) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    m.performAction("R");
                    break;
                case 2:
                    m.performAction("U");
                    break;
                case 3:
                    m.performAction("D");
                    break;
                case 4:
                    m.performAction("L");
            }
            System.out.println(m.printMatrix()+"\n----------------------");
        }while (repeat);

    }
}

