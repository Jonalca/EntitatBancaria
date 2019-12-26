package entitatbancaria.control;

import entitatbancaria.model.dades.DaoClient;
import entitatbancaria.vista.VistaClient;
import java.io.IOException;

public class ControlClient implements Iniciable{

    private VistaClient vistaClient = new VistaClient();
    private DaoClient daoClient;

    public ControlClient(String rutaFitxer, String separador) {
        daoClient = new DaoClient(rutaFitxer, separador);
    }

    @Override
    public void iniciar() {

        boolean continuar = true;

        do {
            switch (vistaClient.mostrarMenu()) {
                case "1":
                    afegir();
                    break;
                case "2":
                    cercarPerNif();
                    break;
                case "3":
                    cercarPerNom();
                    break;
                case "4":
                    cercarTots();
                    break;
                case "5":
                    continuar = false;
                    break;
                default:
                    vistaClient.mostrarInformacio("Introduce opcion correcta");
            }
        } while (continuar);
    }

    private void afegir() {
        if (daoClient.afegir(vistaClient.demanarClient())) {
            vistaClient.mostrarInformacio("Client afegit.");
        } else {
            vistaClient.mostrarInformacio("El client no s'afegit.");
        }
    }

    private void cercarPerNif() {
        try {
            vistaClient.imprimir(daoClient.cercarPerNif(vistaClient.demanarText("Introduir el NIF:")));
        } catch (IOException ex) {
            vistaClient.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }

    private void cercarPerNom() {
        try {
            vistaClient.imprimir(daoClient.cercarPerNom(vistaClient.demanarText("Introduir el nom")));
        } catch (IOException ex) {
            vistaClient.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }

    private void cercarTots() {

        try {
            vistaClient.imprimir(daoClient.cercarTots());
        } catch (IOException ex) {
            vistaClient.mostrarInformacio("ERROR: No s'ha pogut realitzar la cerca.");
        }
    }

}
