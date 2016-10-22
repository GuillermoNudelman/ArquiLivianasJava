package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Convenio;

public interface ConvenioService {
    
    public void addConvenio(Convenio convenio);

    public void removeConvenio(Convenio convenio);

    public List<Convenio> listConvenio();
    
    public Convenio buscarConvenio(String codigo);
    
    void editarConvenio(Convenio convenio);
}

