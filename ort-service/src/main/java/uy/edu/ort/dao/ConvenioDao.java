package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Convenio;

/**
 * Interfaz del convenio para 
 * que sea persistida en una base de datos
 */
public interface ConvenioDao {

    void addConvenio(Convenio convenio);

    void removeConvenio(Convenio convenio);

    List<Convenio> listConvenios();

    Convenio buscarConvenio(String codigo);
    
    void editarConvenio(Convenio convenio);
}

