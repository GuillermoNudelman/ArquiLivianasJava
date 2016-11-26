package uy.edu.ort.dao;

import java.util.Date;
import java.util.List;
import uy.edu.ort.model.LogTrazabilidad;

/**
 * Interfaz de la entidad LogTrazabilidad para 
 * que sea persistida en una base de datos
 */
public interface LogTrazabilidadDao {

    void addLogTrazabilidad(LogTrazabilidad logTrazabilidad);

    List<LogTrazabilidad> promedioEjecucion(Date date);

    LogTrazabilidad servicioMasRapido(Date date);

    LogTrazabilidad servicioMasLento(Date date);

    List<LogTrazabilidad> cantidadLlamadas(Date date);

    LogTrazabilidad servicioMasUsado(Date date);

    LogTrazabilidad servicioMenosUsado(Date date);

}
