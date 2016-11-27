package uy.edu.ort.utilities;

import java.util.Date;
import java.util.List;
import uy.edu.ort.model.LogTrazabilidad;

/**
 * Esta interfaz es utilizada para realizar diversas consultas a la base de
 * datos
 *
 */
public interface LogTrazabilidadService {

    public void addLogTrazabilidad(LogTrazabilidad logTrazabilidad);

    public List<LogTrazabilidad> promedioEjecucion(Date date);

    public LogTrazabilidad servicioMasRapido(Date date);

    public LogTrazabilidad servicioMasLento(Date date);

    public List<LogTrazabilidad> cantidadLlamadas(Date date);

    public LogTrazabilidad servicioMasUsado(Date date);

    public LogTrazabilidad servicioMenosUsado(Date date);

    public List<LogTrazabilidad> listaLogs();

}
