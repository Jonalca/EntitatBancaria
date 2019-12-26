package entitatbancaria.control;

import entitatbancaria.model.dades.DaoClient;
import entitatbancaria.model.dades.DaoCompte;
import entitatbancaria.model.logica.Client;
import entitatbancaria.model.logica.Compte;
import entitatbancaria.vista.VistaCompte;
import java.io.IOException;

public class ControlCompte implements Iniciable{

    private VistaCompte vistaCompte = new VistaCompte();
    private DaoClient daoClient;
    private DaoCompte daoCompte;

    public ControlCompte(String rutaFitxer, String separador, DaoClient daoClient) {
        this.daoClient=daoClient;
        this.daoCompte = new DaoCompte(rutaFitxer, separador, daoClient);
    }

    @Override
    public void iniciar() {

        boolean continuar = true;

        do {
            switch (vistaCompte.mostrarMenu()) {
                case "1":
                    afegir();
                    break;
                case "2":
                    cercarPerNumero();
                    break;
                case "3":
                    cercarPerClient();
                    break;
                case "4":
                    cercarTots();
                    break;
                case "5":
                    continuar = false;
                    break;
                default:
                    vistaCompte.mostrarInformacio("Opció incorrecta");
            }
        } while (continuar);
    }

    private void afegir() {
        if (daoCompte.afegir(vistaCompte.demanarCompte())) {
            vistaCompte.mostrarInformacio("Compte creat.");
        } else {
            vistaCompte.mostrarInformacio("El Compte no s'ha creat.");
        }
    }

    private void cercarPerNumero() {
        try {
            Compte c = daoCompte.cercarPerNumero(vistaCompte.demanarLongPositiu("Introduir el número: "));
            if (c != null) {
                vistaCompte.imprimir(c);
            } else {
                vistaCompte.mostrarInformacio("No existeix el compte.");

            }
        } catch (IOException ex) {
            vistaCompte.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }

    private void cercarPerClient() {
        try {
            int n=34;
            Client c=daoClient.cercarPerNif(
                    vistaCompte.demanarText("Introduir el NIF:"));
            if(c!=null){
                vistaCompte.imprimir(daoCompte.cercaPerClient(c));
            }
        } catch (IOException ex) {
            vistaCompte.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }


    private void cercarTots() {

        try {
            vistaCompte.imprimir(daoCompte.cercarTots());
        } catch (IOException ex) {
            vistaCompte.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }

}
