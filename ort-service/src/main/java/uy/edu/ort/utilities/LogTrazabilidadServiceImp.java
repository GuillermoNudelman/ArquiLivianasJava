/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.utilities;

import uy.edu.ort.utilities.LogTrazabilidadService;
import java.util.Date;
import java.util.List;
import uy.edu.ort.dao.LogTrazabilidadDao;
import uy.edu.ort.model.LogTrazabilidad;

/**
 *
 * @author ptrecca
 */
public class LogTrazabilidadServiceImp implements LogTrazabilidadService{

    private LogTrazabilidadDao logTrazabilidadDao;

    public void setLogTrazabilidadDao(LogTrazabilidadDao logTrazabilidadDao) {
        this.logTrazabilidadDao = logTrazabilidadDao;
    }
    
    @Override
    public void addLogTrazabilidad(LogTrazabilidad logTrazabilidad) {
          this.logTrazabilidadDao.addLogTrazabilidad(logTrazabilidad);
    }

    @Override
    public List<LogTrazabilidad> promedioEjecucion(Date date) {
        return this.logTrazabilidadDao.promedioEjecucion(date);
    }

    @Override
    public LogTrazabilidad servicioMasRapido(Date date) {
        return this.logTrazabilidadDao.servicioMasRapido(date);
    }

    @Override
    public LogTrazabilidad servicioMasLento(Date date) {
        return this.logTrazabilidadDao.servicioMasLento(date);
    }

    @Override
    public List<LogTrazabilidad> cantidadLlamadas(Date date) {
        return this.logTrazabilidadDao.cantidadLlamadas(date);
    }

    @Override
    public LogTrazabilidad servicioMasUsado(Date date) {
         return this.logTrazabilidadDao.servicioMasUsado(date);
    }

    @Override
    public LogTrazabilidad servicioMenosUsado(Date date) {
        return this.logTrazabilidadDao.servicioMenosUsado(date);
    }
   
    
}
