package uy.edu.ort.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import uy.edu.ort.dao.EntregaDao;
import uy.edu.ort.exceptions.ReferenciaNoEncontradaException;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.model.Paquete;

/**
 * Implementacion de la interfaz EntregaService
 *
 */
public class EntregaServiceImpl implements EntregaService {

    private EntregaDao entregaDao;
    private PaqueteService paqueteService;
    private CamionetaService camionetaService;
    

    public void setEntregaDao(EntregaDao entregaDao) {
        this.entregaDao = entregaDao;
    }

    @Override
    @Transactional
    public void addEntrega(Entrega entrega) {
        List<Paquete> paquetes = obtenerListadoPaquetesDisponibles(entrega.getListaPaquetesString());
        if (paquetes.isEmpty()) {
            throw new ReferenciaNoEncontradaException("paquete");
        }
        int pesoTotal = calcularKilos(paquetes);
        if (entrega.getCamioneta().getCapacidadKgs() < pesoTotal) {
            throw new ReferenciaNoEncontradaException("camioneta");
        }
        if (entrega.getCamioneta().getKmsRecorridos() + entrega.getDistanciaRecorrerKm() > entrega.getCamioneta().getKmsProxService()) {
            throw new ReferenciaNoEncontradaException("camioneta");
        }
        if(entregasDelChoferEnFecha(entrega)>1){
            throw new ReferenciaNoEncontradaException("chofer");
        }
        agregarEntregaAPaquetes(paquetes, entrega);
        this.entregaDao.addEntrega(entrega);
        actualizarKmsCamioneta(entrega);
    }

    @Override
    @Transactional
    public void removeEntrega(Entrega entrega) {
        System.out.println("removeEntrega " + entrega.getCodigo());
        this.entregaDao.removeEntrega(entrega);
    }

    @Override
    @Transactional
    public List<Entrega> listEntrega() {
        System.out.println("listEntrega ");
        return this.entregaDao.listEntregas();
    }

    @Override
    @Transactional
    public Entrega buscarEntrega(String codigo) {
        return this.entregaDao.buscarEntrega(codigo);
    }

    @Override
    @Transactional
    public void editarEntrega(Entrega entrega) {
        this.entregaDao.editarEntrega(entrega);
    }

    @Override
    public List<Entrega> listEntregaPorMes(int mes) {
        return this.entregaDao.listEntregaPorMes(mes);
    }

    @Override
    public List<Entrega> listEntregaPorMesYCamioneta(int mes, String codigoCamioneta) {
        return this.entregaDao.listEntregaPorMesYCamioneta(mes, codigoCamioneta);
    }

    @Override
    public List<Entrega> listEntregaPorMesCamionetaYChofer(int mes, String camioneta, String chofer) {
        return this.entregaDao.listEntregaPorMesCamionetaYChofer(mes, camioneta, chofer);
    }

    private List<Paquete> obtenerListadoPaquetesDisponibles(String listaPaquetesString) {
        try {
            String[] idPaquetes = listaPaquetesString.split("-");
            List<Paquete> paquetes = new ArrayList<Paquete>();
            for (int i = 0; i < idPaquetes.length; i++) {
                Paquete p = paqueteService.buscarPaquetePorId(Long.parseLong(idPaquetes[i]));
            //    p.getEntrega()
                if (p != null) {
                    if (!paquetes.contains(p)) {
                        paquetes.add(p);
                    }
                } else {
                    return null;
                }
            }
            return paquetes;
        } catch (Exception e) {
            return null;
        }
    }

    private void agregarEntregaAPaquetes(List<Paquete> paquetes, Entrega entrega) {
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paqNuevo = paquetes.get(i);
            paqNuevo.setEntrega(entrega);
            paqueteService.editarPaquete(paqNuevo);
        }
    }

    private int calcularKilos(List<Paquete> paquetes) {
        int pesoTotal = 0;
        for (int i = 0; i < paquetes.size(); i++) {
            pesoTotal = pesoTotal + paquetes.get(i).getPeso();
        }
        return pesoTotal;
    }

    private int entregasDelChoferEnFecha(Entrega e) {
        int cantEntregasChofer = 0;
        List<Entrega> entregas = listEntrega();
        for (int i = 0; i < entregas.size(); i++) {
            if (entregas.get(i).getChofer().getId() == e.getIdChofer()) {
                cantEntregasChofer++;
            }
        }
        return cantEntregasChofer;
    }
    
    private void actualizarKmsCamioneta(Entrega e){
        Long idCamioneta = Long.valueOf(e.getIdCamioneta());
        Camioneta c = camionetaService.buscarCamionetaPorId(idCamioneta);
        c.setKmsRecorridos(c.getKmsRecorridos() + e.getDistanciaRecorrerKm());
        camionetaService.editarCamioneta(c);
    }
}
