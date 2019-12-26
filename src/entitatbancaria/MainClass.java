package entitatbancaria;

import entitatbancaria.control.Control;

public class MainClass {

    public static void main(String[] args) {

        String rutaFitxerClients = "fitxers/clients";
        String separadorClients = ":";
        String rutaFitxerComptes = "fitxers/comptes";
        String separadorComptes = ":";
        new Control(rutaFitxerClients,separadorClients,rutaFitxerComptes,separadorComptes).iniciar();

    }

}
