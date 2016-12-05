package uy.edu.ort.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import uy.edu.ort.dao.EntregaDao;
import uy.edu.ort.exceptions.ReferenciaNoEncontradaException;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Convenio;
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
    private ConvenioService convenioService;

    private List<Convenio> conveniosActualizar = new ArrayList();

    public void setEntregaDao(EntregaDao entregaDao) {
        this.entregaDao = entregaDao;
    }

    public PaqueteService getPaqueteService() {
        return paqueteService;
    }

    public void setPaqueteService(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    public CamionetaService getCamionetaService() {
        return camionetaService;
    }

    public void setCamionetaService(CamionetaService camionetaService) {
        this.camionetaService = camionetaService;
    }

    public ConvenioService getConvenioService() {
        return convenioService;
    }

    public void setConvenioService(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @Override
    @Transactional
    public void addEntrega(Entrega entrega) {
        List<Paquete> paquetes = obtenerListadoPaquetesDisponibles(entrega.getListaPaquetesString());
        if (paquetes == null) {
            throw new ReferenciaNoEncontradaException("paquete");
        } else if (paquetes.isEmpty()) {
            throw new ReferenciaNoEncontradaException("paquete");
        }

        double costoTotalEntrega = obtenerCostoTotalEntrega(paquetes, entrega);
        entrega.setImporteEntrega((int) costoTotalEntrega);

        if (entrega.getFechaEntrega().before(Calendar.getInstance().getTime())) {
            throw new ReferenciaNoEncontradaException("fecha_entrega");
        }

        int pesoTotal = calcularKilos(paquetes);
        if (entrega.getCamioneta().getCapacidadKgs() < pesoTotal) {
            throw new ReferenciaNoEncontradaException("camioneta");
        }
        if (entrega.getCamioneta().getKmsRecorridos() + entrega.getDistanciaRecorrerKm() > entrega.getCamioneta().getKmsProxService()) {
            throw new ReferenciaNoEncontradaException("camioneta");
        }
        if (entregasDelChoferEnFecha(entrega) > 1) {
            throw new ReferenciaNoEncontradaException("chofer");
        }
        this.entregaDao.addEntrega(entrega);
        entrega = this.buscarEntrega(entrega.getCodigo());
        agregarEntregaAPaquetes(paquetes, entrega);

        actualizarKmsCamioneta(entrega);
        actualizarConvenios();
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
    public List<Entrega> listEntregaPorMesYCamioneta(int mes, int codigoCamioneta) {
        return this.entregaDao.listEntregaPorMesYCamioneta(mes, codigoCamioneta);
    }

    @Override
    public List<Entrega> listEntregaPorMesYChofer(int mes, int idChofer) {
        return this.entregaDao.listEntregaPorMesYChofer(mes, idChofer);
    }

    private List<Paquete> obtenerListadoPaquetesDisponibles(String listaPaquetesString) {
        try {
            String[] idPaquetes = listaPaquetesString.split("-");
            List<Paquete> paquetes = new ArrayList<Paquete>();
            for (int i = 0; i < idPaquetes.length; i++) {
                Paquete p = paqueteService.buscarPaquetePorId(Long.parseLong(idPaquetes[i]));
                if (p != null) {
                    if (p.getEntrega() == null) {
                        if (!paquetes.contains(p)) {
                            paquetes.add(p);
                        }
                    } else {
                        return null;
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
            Entrega entrega = entregas.get(i);
            if (entrega.getChofer().getId() == e.getIdChofer() && entrega.getFechaEntrega() == e.getFechaEntrega()) {
                cantEntregasChofer++;
            }
        }
        return cantEntregasChofer;
    }

    private void actualizarKmsCamioneta(Entrega e) {
        Camioneta c = camionetaService.buscarCamioneta(e.getCamioneta().getCodigo());
        c.setKmsRecorridos(c.getKmsRecorridos() + e.getDistanciaRecorrerKm());
        camionetaService.editarCamioneta(c);
    }

    private double obtenerCostoTotalEntrega(List<Paquete> paquetes, Entrega e) {
        double costoTotal = 0;
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paq = paquetes.get(i);
            double costoPaquete = paq.getCosto();

            Convenio c = obtenerConvenioSinUso(paq, e);
            if (c != null) {  //si existen convenios
                int importeRestante = c.getImporteInicialConvenio() - c.getImporteActualConvenio();
                if (paq.getCosto() < importeRestante) {
                    c.setImporteActualConvenio(c.getImporteActualConvenio() + paq.getCosto());
                    costoPaquete = 0;
                } else {
                    c.setImporteActualConvenio(c.getImporteInicialConvenio());

                    //aplico el monto del convenio
                    costoPaquete = costoPaquete - importeRestante;

                    //Se realiza el 20% 
                    costoPaquete = costoPaquete - (costoPaquete * 0.2);
                }
                conveniosActualizar.add(c);
            }
            costoTotal += costoPaquete;
        }
        return costoTotal;
    }

    private Convenio obtenerConvenioSinUso(Paquete paquete, Entrega e) {
        List<Convenio> convenios = convenioService.listConvenio();
        Convenio c = null;
        for (int i = 0; i < convenios.size(); i++) {
            Convenio conv = convenios.get(i);
            if (conv.getCliente() != null) {
                if (conv.getCliente().getId() == paquete.getCliente().getId() && !conv.isEstaEnUso() && !conveniosActualizar.contains(conv)) {
                    return conv;
                }
            }
        }
        return c;
    }

    private void actualizarConvenios() {
        for (int i = 0; i < conveniosActualizar.size(); i++) {
            Convenio convenioActual = conveniosActualizar.get(i);
            convenioActual.setEstaEnUso(true);
            convenioService.editarConvenio(convenioActual);
        }
    }
}
