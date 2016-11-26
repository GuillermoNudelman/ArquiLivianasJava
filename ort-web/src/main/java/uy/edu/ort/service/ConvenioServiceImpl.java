package uy.edu.ort.service;
import java.util.List;
import javax.transaction.Transactional;
import uy.edu.ort.dao.ConvenioDao;
import uy.edu.ort.model.Convenio;

/**
 * Implementacion de la interfaz ConvenioService
 * 
 */
public class ConvenioServiceImpl implements ConvenioService {

    private ConvenioDao convenioDao;

    public void setConvenioDao(ConvenioDao convenioDao) {
        this.convenioDao = convenioDao;
    }

    @Override
    @Transactional
    public void addConvenio(Convenio convenio) {
        System.out.println("addConvenio " + convenio.getCodigo());
        this.convenioDao.addConvenio(convenio);
    }

    @Override
    @Transactional
    public void removeConvenio(Convenio convenio) {
        System.out.println("removeConvenio " + convenio.getCodigo());
        this.convenioDao.removeConvenio(convenio);
    }

    @Override
    @Transactional
    public List<Convenio> listConvenio() {
        System.out.println("listConvenios ");
        return this.convenioDao.listConvenios();
    }
    
    @Override
    @Transactional
    public Convenio buscarConvenio(String codigo) {
        return this.convenioDao.buscarConvenio(codigo);
    }
    
    @Override
    @Transactional
    public Convenio buscarConvenioPorId(Long id) {
        return this.convenioDao.buscarConvenioPorId(id);
    }
    
    @Override
    @Transactional
    public void editarConvenio(Convenio convenio) {
          this.convenioDao.editarConvenio(convenio);
    }
}

