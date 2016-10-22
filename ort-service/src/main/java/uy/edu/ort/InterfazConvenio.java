/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainUserService.esFecha;
import static uy.edu.ort.MainUserService.esPositivo;
import static uy.edu.ort.MainUserService.validarOpcion;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.service.ConvenioService;

/**
 *
 * @author Guillermo
 */
public final class InterfazConvenio {
        public static void ConvenioInferfaz(ApplicationContext applicationContext){
        
        ConvenioService convenioService = (ConvenioService) applicationContext.getBean("convenioService");
         
        String[] menu = {"Ingresar Convenio", "Editar Convenio", "Eliminar Convenio", "Listar Convenios", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Convenio");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);            
            switch (opcion) {
                case 1: {
                    Convenio convenio = new Convenio();
                    System.out.println("Código: ");
                    convenio.setCodigo(in.nextLine());
                    System.out.println("Fecha de creación (ej. 31-12-2016): ");
                    convenio.setFechaCreacion(esFecha(in.nextLine()));
                    System.out.println("Importe Inicial: ");
                    convenio.setImporteInicialConvenio((int) esPositivo(in));
                    System.out.println("Importe Actual: ");
                    convenio.setImporteActualConvenio((int) esPositivo(in));
                    convenioService.addConvenio(convenio);
                    break;
                }
                case 2: {
                    System.out.println("Codigo: ");
                    Convenio convenio = convenioService.buscarConvenio(in.nextLine());
                    if(convenio == null){
                        System.out.println("No existe un convenio con ese codigo.");
                    }else{
                        System.out.println("Fecha de creación (ej. 31-12-2016): ");
                        convenio.setFechaCreacion(esFecha(in.nextLine()));
                        System.out.println("Importe Inicial: ");
                        convenio.setImporteInicialConvenio((int) esPositivo(in));
                        System.out.println("Importe Actual: ");
                        convenio.setImporteActualConvenio((int) esPositivo(in));                  
                        convenioService.addConvenio(convenio);
                    }
                    break;
                }
                case 3: {                   
                    System.out.println("Codigo: ");
                    Convenio convenio = convenioService.buscarConvenio(in.nextLine());
                    if(convenio == null){
                        System.out.println("No existe un convenio con ese codigo.");
                    }else{
                        convenioService.removeConvenio(convenio);
                        System.out.println("El convenio ha sido eliminada con exito.");
                    }
                    break;
                }
                case 4: {
                    List<Convenio> convenios = convenioService.listConvenio();
                    for (Convenio c : convenios) {
                        System.out.println("Código de convenio: " + c.getCodigo());
                        System.out.println("    Importe inicial: " + c.getImporteInicialConvenio());
                        System.out.println("    Importe actual: " + c.getImporteActualConvenio());
                        System.out.println("");
                    }
                    break;
                }
                default :{}    

            }
        }
        
    
    }
}
