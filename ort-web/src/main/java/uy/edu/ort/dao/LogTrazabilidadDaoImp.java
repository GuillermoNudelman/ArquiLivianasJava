package uy.edu.ort.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.LogTrazabilidad;

/**
 * Implementacion de LogTrazabilidadDao, a una base de datos mysql utilizando
 * hibernate
 */
public class LogTrazabilidadDaoImp implements LogTrazabilidadDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addLogTrazabilidad(LogTrazabilidad logTrazabilidad) {
        this.hibernateTemplate.save(logTrazabilidad);
    }

    @Override
    public List<LogTrazabilidad> promedioEjecucion(Date date) {
        Object[] params = {date};
        List<Object[]> listaObtenida = (List<Object[]>) hibernateTemplate.find("SELECT l.nombreOperacion , avg(l.tiempoEjecucion) FROM LogTrazabilidad l where l.fechaOperacion = ? group by l.nombreOperacion", params);
        List<LogTrazabilidad> lista = new ArrayList<LogTrazabilidad>();
        for (Object[] log : listaObtenida) {
            LogTrazabilidad logaux = new LogTrazabilidad();
            logaux.setNombreOperacion((String) log[0]);
            double time = (double) log[1];
            long timelong = Math.round(time);
            logaux.setTiempoEjecucion(timelong);
            lista.add(logaux);
        }
        return lista;
    }

    @Override
    public LogTrazabilidad servicioMasRapido(Date date) {
        Object[] params = {date};
        List<LogTrazabilidad> listaObtenida = (List<LogTrazabilidad>) hibernateTemplate.find("SELECT l FROM LogTrazabilidad l where l.fechaOperacion = ?", params);
        LogTrazabilidad logResultado = null;
        for (LogTrazabilidad log : listaObtenida) {
            if (logResultado == null) {
                logResultado = log;
            } else {
                logResultado = logResultado.getTiempoEjecucion() < log.getTiempoEjecucion() ? logResultado : log;
            }
        }
        return logResultado;
    }

    @Override
    public LogTrazabilidad servicioMasLento(Date date) {
        Object[] params = {date};
        List<LogTrazabilidad> listaObtenida = (List<LogTrazabilidad>) hibernateTemplate.find("SELECT l FROM LogTrazabilidad l where l.fechaOperacion = ?", params);
        LogTrazabilidad logResultado = null;
        for (LogTrazabilidad log : listaObtenida) {
            if (logResultado == null) {
                logResultado = log;
            } else {
                logResultado = logResultado.getTiempoEjecucion() > log.getTiempoEjecucion() ? logResultado : log;
            }
        }
        return logResultado;
    }

    /**
     * Este medodo retorna la cantidad de llamadas de cada servicio Reutiliza la
     * clase LogTrazabilidad setando en tiempo de ejecucion la cantidad de
     * llamadas
     *
     * @param date
     * @return List<LogTrazabilidad>
     */
    @Override
    public List<LogTrazabilidad> cantidadLlamadas(Date date) {
        Object[] params = {date};
        List<Object[]> listaObtenida = (List<Object[]>) hibernateTemplate.find("SELECT l.nombreOperacion , count(l.nombreOperacion) FROM LogTrazabilidad l where l.fechaOperacion = ? group by l.nombreOperacion", params);
        List<LogTrazabilidad> lista = new ArrayList<LogTrazabilidad>();
        for (Object[] log : listaObtenida) {
            LogTrazabilidad logaux = crearLogTrazabilidad(log);
            lista.add(logaux);
        }
        return lista;
    }

    @Override
    public LogTrazabilidad servicioMasUsado(Date date) {
        Object[] params = {date};
        List<Object[]> listaObtenida = (List<Object[]>) hibernateTemplate.find("SELECT l.nombreOperacion , count(l.nombreOperacion) FROM LogTrazabilidad l where l.fechaOperacion = ? group by l.nombreOperacion", params);
        LogTrazabilidad logResultado = null;
        for (Object[] log : listaObtenida) {
            LogTrazabilidad logaux = crearLogTrazabilidad(log);
            if (logResultado == null) {
                logResultado = logaux;
            } else {
                logResultado = logResultado.getTiempoEjecucion() > logaux.getTiempoEjecucion() ? logResultado : logaux;
            }
        }
        return logResultado;
    }

    @Override
    public LogTrazabilidad servicioMenosUsado(Date date) {
        Object[] params = {date};
        List<Object[]> listaObtenida = (List<Object[]>) hibernateTemplate.find("SELECT l.nombreOperacion , count(l.nombreOperacion) FROM LogTrazabilidad l where l.fechaOperacion = ? group by l.nombreOperacion", params);
        LogTrazabilidad logResultado = null;
        for (Object[] log : listaObtenida) {
            LogTrazabilidad logaux = crearLogTrazabilidad(log);
            if (logResultado == null) {
                logResultado = logaux;
            } else {
                logResultado = logResultado.getTiempoEjecucion() < logaux.getTiempoEjecucion() ? logResultado : logaux;
            }
        }
        return logResultado;
    }

    // crea un log a partir del resultado de una busqueda en la base de datos
    private LogTrazabilidad crearLogTrazabilidad(Object[] obj) {

        LogTrazabilidad log = new LogTrazabilidad();
        log.setNombreOperacion((String) obj[0]);
        log.setTiempoEjecucion((long) obj[1]);
        return log;
    }

    @Override
    public List<LogTrazabilidad> listaLogs() {
        List<LogTrazabilidad> listaObtenida = (List<LogTrazabilidad>) hibernateTemplate.find("SELECT l FROM LogTrazabilidad l order by l.fechaOperacion");
        return listaObtenida;
    }

}
