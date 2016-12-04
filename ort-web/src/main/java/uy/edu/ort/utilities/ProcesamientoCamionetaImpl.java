/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import uy.edu.ort.dao.ProcesamientoCamionetaDao;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.model.ProcesamientoCamioneta;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;

/**
 *
 * @author ptrecca
 */
public class ProcesamientoCamionetaImpl implements ProcesamientoCamionetaService {

    private ProcesamientoCamionetaDao procesamientoCamionetaDao;

    private EntregaService entregaService;

    private PaqueteService paqueteService;
    
    private CamionetaService camionetaService;

    public void setProcesamientoCamionetaDao(ProcesamientoCamionetaDao procesamientoCamionetaDao) {
        this.procesamientoCamionetaDao = procesamientoCamionetaDao;
    }

    public void setCamionetaService(CamionetaService camionetaService) {
        this.camionetaService = camionetaService;
    }

    public void setPaqueteService(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    public void setEntregaService(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @Override
    public void addProcesamientoCamioneta(Camioneta camioneta) {
        camioneta = camionetaService.buscarCamioneta(camioneta.getCodigo());
        Date todayDate = new Date();
        Date lastDate = this.addDays(todayDate, -7);
        todayDate = new Date();
        List<Entrega> entregas = entregaService.listEntrega();
        List<Entrega> entregasAProcesar = new ArrayList<Entrega>();
        List<Paquete> paquetes = paqueteService.listPaquetes();
        for (Entrega ent : entregas) {
            if (ent.getCamioneta() != null) {
                if (ent.getCamioneta().getCodigo().equals(camioneta.getCodigo())) {
                    if (ent.getFechaEntrega().equals(todayDate) || ent.getFechaEntrega().equals(lastDate) || (ent.getFechaEntrega().before(todayDate) && ent.getFechaEntrega().after(lastDate))) {
                        entregasAProcesar.add(ent);
                    }
                }
            }
        }
        int kilometrosRecorridos = 0;
        int pesoTotal = 0;
        boolean esValida = true;
        for (Entrega ent : entregasAProcesar) {
            int pesoTransportado = 0;
            for(Paquete p : paquetes){
                if(p.getEntrega() != null){
                    if(p.getEntrega().getCodigo().equals(ent.getCodigo())){
                        pesoTransportado += p.getPeso();
                    }
                }
            }
            if(pesoTransportado > camioneta.getCapacidadKgs()){
                esValida = false;
            }
            pesoTotal += pesoTransportado;
            kilometrosRecorridos += ent.getDistanciaRecorrerKm();
        }

        ProcesamientoCamioneta pc = new ProcesamientoCamioneta();
        pc.setCamioneta(camioneta);
        pc.setKilometrosRecorridos((long)kilometrosRecorridos);
        pc.setPesoTransportado((long)pesoTotal);
        pc.setValido(esValida);
        pc.setPeriodo(todayDate.toString() + "hasta" + lastDate.toString());
        this.procesamientoCamionetaDao.addProcesamientoCamioneta(pc);
    }

    @Override
    public List<ProcesamientoCamioneta> listaProcesamientoCamioneta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date addDays(Date d, int days) {
        Calendar calendar = Calendar.getInstance(); // this would default to now
        calendar.add(Calendar.DAY_OF_MONTH, days);
        d.setTime(calendar.getTime().getTime());
        return d;
    }

}
