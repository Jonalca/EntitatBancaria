package entitatbancaria.vista;

import java.util.Scanner;


public class Vista {
    public String mostrarMenu(){

        System.out.println("\nIntroduir una opció: "
                + "\n1. Clients"
                + "\n2. Comptes"
                + "\n3. Sortir");
        return new Scanner(System.in).nextLine();
    }

    public void mostrarInformacio(String info){
        System.out.println(info);
    }
    public String demanarText(String info) {
        Scanner lector = new Scanner(System.in);
        System.out.print(info);
        return lector.nextLine();
    }
}

