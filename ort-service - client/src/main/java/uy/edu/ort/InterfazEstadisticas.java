package uy.edu.ort;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainClient.esFecha;
import static uy.edu.ort.MainClient.validarOpcion;
import uy.edu.ort.model.LogTrazabilidad;
import uy.edu.ort.utilities.LogTrazabilidadService;

public final class InterfazEstadisticas {

    public static void EstadisticasInferfaz(ApplicationContext applicationContext) {
        
        LogTrazabilidadService logTrazabilidadService = (LogTrazabilidadService) applicationContext.getBean("logTrazabilidadService");
         
        String[] menu = {"Tiempo promedio de ejecución de cada servicio", "Tiempo de ejecución del servicio mas rápido", "Tiempo de ejecución del servicio mas lento", "Cantidad de veces que se ha llamado cada servicio", "Servicio mas usado", "Servicio menos usado", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Profiling y Estadística");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);
            switch (opcion) {
                case 1: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    List<LogTrazabilidad> lista = logTrazabilidadService.promedioEjecucion(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (lista.size() > 0) {
                        Object a = lista.indexOf(0);
                        for (LogTrazabilidad logTrazabilidad : lista) {
                            System.out.println("Servicio: " + logTrazabilidad.getNombreOperacion() + " Promedio: " + logTrazabilidad.getTiempoEjecucion());
                        }
                    } else {
                        System.out.println("No se encontraron resultados");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    LogTrazabilidad log = logTrazabilidadService.servicioMasRapido(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (log != null) {
                       System.out.println("Servicio: " + log.getNombreOperacion() + " Tiempo de ejecucion: " + log.getTiempoEjecucion());
                    }else{
                         System.out.println("No se encontraron resultados");
                    }

                    break;
                }

                case 3: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    LogTrazabilidad log = logTrazabilidadService.servicioMasLento(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (log != null) {
                        System.out.println("Servicio: " + log.getNombreOperacion() + " Tiempo de ejecucion: " + log.getTiempoEjecucion());
                    }else{
                         System.out.println("No se encontraron resultados");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    List<LogTrazabilidad> lista = logTrazabilidadService.cantidadLlamadas(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (lista.size() > 0) {
                        for (LogTrazabilidad logTrazabilidad : lista) {
                            System.out.println("Servicio: " + logTrazabilidad.getNombreOperacion() + " Cantidad de llamadas: " + logTrazabilidad.getTiempoEjecucion());
                        }
                    } else {
                        System.out.println("No se encontraron resultados");
                    }                   
                    break;
                }
                case 5: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    LogTrazabilidad log = logTrazabilidadService.servicioMasUsado(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (log != null) {
                       System.out.println("Servicio: " + log.getNombreOperacion() + " Cantidad de llamadas: " + log.getTiempoEjecucion());
                    }else{
                         System.out.println("No se encontraron resultados");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Fecha (ej. 31-12-2016): ");
                    LogTrazabilidad log = logTrazabilidadService.servicioMenosUsado(esFecha(in.nextLine()));
                    System.out.println("Resultado: ");
                    if (log != null) {
                         System.out.println("Servicio: " + log.getNombreOperacion() + " Cantidad de llamadas: " + log.getTiempoEjecucion());
                    }else{
                         System.out.println("No se encontraron resultados");
                    }
                    break;
                }
                default: {
                }
            }
        }
    }
}
