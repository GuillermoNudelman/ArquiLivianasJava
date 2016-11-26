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
 * Esta clase expone los servicios REST con el resultado de consultas sobre estaditica
 */
@Controller
@RequestMapping("/estaditicas")
public class EstadisticasController {
    
    
    @Autowired
    private LogTrazabilidadService logTrazabilidadService;
    
    @RequestMapping(value = "/tiempoPromedioServicio/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Map<String, String>,Map<String, Long>>> tiempoPromedioServicio(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        List<LogTrazabilidad> logs = logTrazabilidadService.promedioEjecucion(fecha);
        List<Map<Map<String, String>,Map<String, Long>>> listaEstaditica = new ArrayList<Map<Map<String, String>,Map<String, Long>>>();
        for(LogTrazabilidad trz : logs){
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", trz.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Tiempo de ejecucion", trz.getTiempoEjecucion());
            Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
            obj.put(objServ, objTiempo);
            listaEstaditica.add(obj);            
        }
        return listaEstaditica;
    }    
    
    @RequestMapping(value = "/tiempoServicioMasRapido/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>,Map<String, Long>> tiempoServicioMasRapido(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasRapido(fecha);
        Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
      
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", log.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Tiempo de ejecucion", log.getTiempoEjecucion());           
            obj.put(objServ, objTiempo);
       
        return obj;
    }    
    
    @RequestMapping(value = "/tiempoServicioMasLento/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>,Map<String, Long>> tiempoServicioMasLento(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasLento(fecha);
        Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
      
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", log.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Tiempo de ejecucion", log.getTiempoEjecucion());           
            obj.put(objServ, objTiempo);
       
        return obj;
    }
    
    @RequestMapping(value = "/cantidadLlamadasServicio/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Map<String, String>,Map<String, Long>>> cantidadLlamadasServicio(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        List<LogTrazabilidad> logs = logTrazabilidadService.cantidadLlamadas(fecha);
        List<Map<Map<String, String>,Map<String, Long>>> listaEstaditica = new ArrayList<Map<Map<String, String>,Map<String, Long>>>();
        for(LogTrazabilidad trz : logs){
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", trz.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Llamadas", trz.getTiempoEjecucion());
            Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
            obj.put(objServ, objTiempo);
            listaEstaditica.add(obj);            
        }
        return listaEstaditica;
    }  
    
     @RequestMapping(value = "/servicioMasUsado/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>,Map<String, Long>> servicioMasUsado(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMasUsado(fecha);
        Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
      
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", log.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Llamadas", log.getTiempoEjecucion());           
            obj.put(objServ, objTiempo);
       
        return obj;
    }    
    
    @RequestMapping(value = "/servicioMenosUsado/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Map<String, String>,Map<String, Long>> servicioMenosUsado(@PathVariable("fecha")  @DateTimeFormat(pattern="dd-MM-yyyy") Date fecha) {
        LogTrazabilidad log = logTrazabilidadService.servicioMenosUsado(fecha);
        Map<Map<String, String>,Map<String, Long>> obj = new HashMap<Map<String, String>,Map<String, Long>>();
      
            Map<String, String> objServ = new  HashMap<String, String>();
            objServ.put("Servicio", log.getNombreOperacion());
            Map<String, Long> objTiempo = new  HashMap<String, Long>();
            objTiempo.put("Llamadas", log.getTiempoEjecucion());           
            obj.put(objServ, objTiempo);
       
        return obj;
    }

    
    
}
