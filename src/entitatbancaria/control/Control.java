package entitatbancaria.control;

import entitatbancaria.model.dades.DaoClient;
import entitatbancaria.vista.Vista;

public class Control implements Iniciable {

    private ControlClient ctrlClient;
    private ControlCompte ctrlCompte;
    private Vista vista = new Vista();

    public Control(String rutaFitxerClients, String separadorClients,
                   String rutaFitxerComptes, String separadorComptes) {

        ctrlClient = new ControlClient(rutaFitxerClients, separadorClients);
        ctrlCompte = new ControlCompte(rutaFitxerComptes, separadorComptes,
                new DaoClient(rutaFitxerClients, separadorClients));
    }

    @Override
    public void iniciar() {
        boolean salir = true;
        do {
            switch (vista.mostrarMenu()) {
                case "1":
                    ctrlClient.iniciar();
                    break;
                case "2":
                    ctrlCompte.iniciar();
                    break;
                case "3":
                    salir = false;
                    break;
                default:
                    vista.mostrarInformacio("Introduce opcion correcta");
            }
        } while (salir);
    }
}
