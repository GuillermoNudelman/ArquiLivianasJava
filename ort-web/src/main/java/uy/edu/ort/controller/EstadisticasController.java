/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.edu.ort.model.LogTrazabilidad;
import uy.edu.ort.utilities.LogTrazabilidadService;

/**
 *
 * Esta clase expone los servicios REST con el resultado de consultas sobre
 * estaditica
 */
@Controller
@RequestMapping("/estaditicas")
public class EstadisticasController {

    @Autowired
    private LogTrazabilidadService logTrazabilidadService;

    @RequestMapping(value = "/tiempoPromedioServicio/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Map<String, String>, Map<String, Long>>> tiempoPromedioServicio(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        List<LogTrazabilidad> logs = logTrazabilidadService.promedioEjecucion(fecha);
        List<Map<Map<String, String>, Map<String, Long>>> listaEstaditica = new ArrayList<Map<Map<String, String>, Map<String, Long>>>();
        for (LogTrazabilidad trz : logs) {
            Map<String, String> objServ = new HashMap<String, String>();
            objServ.put("Servicio", trz.getNombreOperacion());
            Map<String, Long> objTiempo = new HashMap<String, Long>();
            objTiempo.put("Tiempo de ejecucion", trz.getTiempoEjecucion());
            Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();
            obj.put(objServ, objTiempo);
            listaEstaditica.add(obj);
        }
        return listaEstaditica;
    }

    @RequestMapping(value = "/tiempoServicioMasRapido/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>, Map<String, Long>> tiempoServicioMasRapido(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasRapido(fecha);
        Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();

        Map<String, String> objServ = new HashMap<String, String>();
        objServ.put("Servicio", log.getNombreOperacion());
        Map<String, Long> objTiempo = new HashMap<String, Long>();
        objTiempo.put("Tiempo de ejecucion", log.getTiempoEjecucion());
        obj.put(objServ, objTiempo);

        return obj;
    }

    @RequestMapping(value = "/tiempoServicioMasLento/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>, Map<String, Long>> tiempoServicioMasLento(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasLento(fecha);
        Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();

        Map<String, String> objServ = new HashMap<String, String>();
        objServ.put("Servicio", log.getNombreOperacion());
        Map<String, Long> objTiempo = new HashMap<String, Long>();
        objTiempo.put("Tiempo de ejecucion", log.getTiempoEjecucion());
        obj.put(objServ, objTiempo);

        return obj;
    }

    @RequestMapping(value = "/cantidadLlamadasServicio/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Map<String, String>, Map<String, Long>>> cantidadLlamadasServicio(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        List<LogTrazabilidad> logs = logTrazabilidadService.cantidadLlamadas(fecha);
        List<Map<Map<String, String>, Map<String, Long>>> listaEstaditica = new ArrayList<Map<Map<String, String>, Map<String, Long>>>();
        for (LogTrazabilidad trz : logs) {
            Map<String, String> objServ = new HashMap<String, String>();
            objServ.put("Servicio", trz.getNombreOperacion());
            Map<String, Long> objTiempo = new HashMap<String, Long>();
            objTiempo.put("Llamadas", trz.getTiempoEjecucion());
            Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();
            obj.put(objServ, objTiempo);
            listaEstaditica.add(obj);
        }
        return listaEstaditica;
    }

    @RequestMapping(value = "/servicioMasUsado/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>, Map<String, Long>> servicioMasUsado(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasUsado(fecha);
        Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();

        Map<String, String> objServ = new HashMap<String, String>();
        objServ.put("Servicio", log.getNombreOperacion());
        Map<String, Long> objTiempo = new HashMap<String, Long>();
        objTiempo.put("Llamadas", log.getTiempoEjecucion());
        obj.put(objServ, objTiempo);

        return obj;
    }

    @RequestMapping(value = "/servicioMenosUsado/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>, Map<String, Long>> servicioMenosUsado(@PathVariable("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMenosUsado(fecha);
        Map<Map<String, String>, Map<String, Long>> obj = new HashMap<Map<String, String>, Map<String, Long>>();

        Map<String, String> objServ = new HashMap<String, String>();
        objServ.put("Servicio", log.getNombreOperacion());
        Map<String, Long> objTiempo = new HashMap<String, Long>();
        objTiempo.put("Llamadas", log.getTiempoEjecucion());
        obj.put(objServ, objTiempo);

        return obj;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<List<Object>> listaTrazabilidad() {
        List<LogTrazabilidad> logs = logTrazabilidadService.listaLogs();
        List<List<Object>> listaRes = new ArrayList<List<Object>>();
        List<Object> lista = new ArrayList<Object>();
        if (!logs.isEmpty()) {
            Date fecha = logs.get(0).getFechaOperacion();
            Map<String, String> map = new HashMap<String, String>();
            map.put("Fecha", fecha.toString());
            lista.add(map);
            for (LogTrazabilidad log : logs) {
                if (log.getFechaOperacion().equals(fecha)) {
                    Map<String, LogTrazabilidad> mapLog = new HashMap<String, LogTrazabilidad>();
                    mapLog.put("Log", log);
                    lista.add(mapLog);
                } else {
                    Map<String, List<String>> mapusuario = new HashMap<String, List<String>>();
                    mapusuario.put("Usuarios con mas operaciones", UsuarioMasOperaciones(fecha, logs));
                    mapusuario.put("Usuarios con menos operaciones", UsuarioMenosOperaciones(fecha, logs));
                    lista.add(mapusuario);
                    listaRes.add(lista);
                    fecha = log.getFechaOperacion();
                    lista = new ArrayList<Object>();
                    map.put("Fecha", fecha.toString());
                    lista.add(map);
                    Map<String, LogTrazabilidad> mapLog = new HashMap<String, LogTrazabilidad>();
                    mapLog.put("Log", log);
                    lista.add(mapLog);
                }
            }
            Map<String, List<String>> mapusuario = new HashMap<String, List<String>>();
            mapusuario.put("Usuarios con mas operaciones", UsuarioMasOperaciones(fecha, logs));
            mapusuario.put("Usuarios con menos operaciones", UsuarioMenosOperaciones(fecha, logs));
            lista.add(mapusuario);
            listaRes.add(lista);
        }
        return listaRes;
    }

    public List<String> UsuarioMasOperaciones(Date fecha, List<LogTrazabilidad> listaLog) {
        List<String> listaUsuarioAux = new ArrayList<String>();
        List<String> listaUsuario = new ArrayList<String>();
        int countMax = 0;
        String usuario = "";
        for (LogTrazabilidad log : listaLog) {
            if (!existeUsuarioEnLista(log.getUsuario(), listaUsuarioAux) && log.getFechaOperacion().equals(fecha)) {
                listaUsuarioAux.add(log.getUsuario());
            }
        }
        for (String usu : listaUsuarioAux) {
            int count = 0;
            for (LogTrazabilidad log : listaLog) {
                if (log.getUsuario().equals(usu) && log.getFechaOperacion().equals(fecha)) {
                    count++;
                }
            }
            if (count > countMax) {
                countMax = count;
                listaUsuario = new ArrayList<String>();
                listaUsuario.add(usu);
            } else if (count == countMax) {
                listaUsuario.add(usu);
            }
        }

        return listaUsuario;

    }

    public List<String> UsuarioMenosOperaciones(Date fecha, List<LogTrazabilidad> listaLog) {
        List<String> listaUsuarioAux = new ArrayList<String>();
        List<String> listaUsuario = new ArrayList<String>();
        int countMax = -1;
        String usuario = "";
        for (LogTrazabilidad log : listaLog) {
            if (!existeUsuarioEnLista(log.getUsuario(), listaUsuarioAux) && log.getFechaOperacion().equals(fecha)) {
                listaUsuarioAux.add(log.getUsuario());
            }
        }

        for (String usu : listaUsuarioAux) {
            int count = 0;
            for (LogTrazabilidad log : listaLog) {
                if (log.getUsuario().equals(usu) && log.getFechaOperacion().equals(fecha)) {
                    count++;
                }
            }
            if (countMax == -1) {
                countMax = count;
                listaUsuario.add(usu);
            } else if (count < countMax) {
                countMax = count;
                listaUsuario = new ArrayList<String>();
                listaUsuario.add(usu);
            } else if (count == countMax) {
                listaUsuario.add(usu);
            }
        }

        return listaUsuario;

    }

    private boolean existeUsuarioEnLista(String usuario, List<String> lista) {
        boolean existe = false;
        if (!lista.isEmpty()) {
            for (String usu : lista) {
                if (usuario.equals(usu)) {
                    existe = true;
                }
            }
        }
        return existe;
    }
}
