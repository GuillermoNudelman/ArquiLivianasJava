/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import static uy.edu.ort.MainUserService.ListarCamionetas_PesoYDistancia;
import static uy.edu.ort.MainUserService.ListarEntregas;
import static uy.edu.ort.MainUserService.ListarPaquetes;
import static uy.edu.ort.MainUserService.ListarPaquetesSinEntrega;
import static uy.edu.ort.MainUserService.esFecha;
import static uy.edu.ort.MainUserService.esPositivo;
import static uy.edu.ort.MainUserService.esUnPaquete;
import static uy.edu.ort.MainUserService.esCamionetaUtil;
import static uy.edu.ort.MainUserService.validarOpcion;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.model.Entrega;
import uy.edu.ort.service.CamionetaService;
import uy.edu.ort.service.EntregaService;
import uy.edu.ort.service.PaqueteService;

/**
 *
 * @author Guillermo
 */
public final class InterfazEntrega {

    public static void EntregaInferfaz(ApplicationContext applicationContext) {

        EntregaService entregaService = (EntregaService) applicationContext.getBean("entregaService");
        CamionetaService camionetaService = (CamionetaService) applicationContext.getBean("camionetaService");
        PaqueteService paqueteService = (PaqueteService) applicationContext.getBean("paqueteService");

        String[] menu = {"Ingresar Entrega", "Listar Entregas", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Entrega");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);
            switch (opcion) {
                case 1: {
                    Entrega entrega = new Entrega();
                    System.out.println("Código: ");
                    entrega.setCodigo(in.nextLine());
                    System.out.println("Fecha de entrega (ej. 31-12-2016): ");
                    entrega.setFechaEntrega(esFecha(in.nextLine()));
                    System.out.println("Distancia a recorrer para realizar la entrega: ");
                    int distanciaARecorrer = (int) esPositivo(in);
                    entrega.setDistanciaRecorrerKm(distanciaARecorrer);
                    

                    //logica para solicitar paquetes
                    System.out.println("Paquete/s a incluir en la entrega (Si selecciona mas de uno, debe separarlos con un guión '-'): ");
                    List<Paquete> listadoPaquetes = paqueteService.listPaquetes();
                    ListarPaquetesSinEntrega(listadoPaquetes);
                    String codigoPaquetes = in.nextLine();
                    List<Paquete> listadoPaquetesAAgregar = agregarPaquetes(codigoPaquetes, listadoPaquetes, paqueteService);
                    
                    //logica para calcular peso total y monto total
                    int pesoTotal = 0;
                    int importeEntrega = 0;
                    for (Paquete p : listadoPaquetesAAgregar) {
                        pesoTotal += p.getPeso();
                        importeEntrega += p.getCosto() - p.getDescuento();
                    }
                    
                    System.out.println("El importe total de la entrega teniendo en cuenta los costos y descuentos de cada paquete es: " + importeEntrega);
                    entrega.setImporteEntrega(importeEntrega);
                    
                    System.out.println("El peso total de los paquetes es: " + pesoTotal);

                    //logica para solicitar las camionetas que cumplan los requisitos de peso, distancia, etc.
                    System.out.println("Código de la camioneta que realiza la entrega (se muestra un listado con las camionetas que cumplen los requisitos de peso y distancia): ");
                    List<Camioneta> listadoCamionetas = camionetaService.listCamioneta();
                    ListarCamionetas_PesoYDistancia(listadoCamionetas, pesoTotal, entrega.getDistanciaRecorrerKm());
                    String codigoCamioneta = in.nextLine();
                    boolean esCamioneta = esCamionetaUtil(codigoCamioneta, listadoCamionetas, pesoTotal, entrega.getDistanciaRecorrerKm());
                    Camioneta camionetaAsociado = new Camioneta();
                    boolean camionetaFueAgregada = false;
                    if (esCamioneta) {
                        camionetaAsociado = camionetaService.buscarCamioneta(codigoCamioneta);
                        entrega.setCamioneta(camionetaAsociado);
                        camionetaFueAgregada = true;
                    }

                    entregaService.addEntrega(entrega);
                    
                    //actualizar paquetes y camioneta
                    asociarPaquetesAEntrega(listadoPaquetesAAgregar, entrega, paqueteService);
                    if (camionetaFueAgregada) {
                        agregarKmsACamioneta(camionetaAsociado, distanciaARecorrer, camionetaService);
                    }
                    
                    break;
                }
                case 2: {
                    ListarEntregas(entregaService.listEntrega(), paqueteService.listPaquetes());
                    break;
                }
                default: {
                }
            }
        }
    }

    private static List<Paquete> agregarPaquetes(String codigoPaquetes, List<Paquete> listadoPaquetes, PaqueteService paqueteService) {
        List<Paquete> paquetesAgregar = new ArrayList<Paquete>();
        String[] listaCodigosPaquete = codigoPaquetes.split("-");
        for (int i = 0; i < listaCodigosPaquete.length; i++) {
            String codigoPaquete = listaCodigosPaquete[i];
            boolean esPaquete = esUnPaquete(codigoPaquete, listadoPaquetes);
            if (esPaquete) {
                Paquete paqueteAsociado = paqueteService.buscarPaquete(codigoPaquete);
                paquetesAgregar.add(paqueteAsociado);
            }
        }
        return paquetesAgregar;
    }

    private static void asociarPaquetesAEntrega(List<Paquete> listadoPaquetesAAgregar, Entrega entrega, PaqueteService paqueteService) {
        for (Paquete p : listadoPaquetesAAgregar) {
            p.setEntrega(entrega);
            paqueteService.editarPaquete(p);
            //actualizar convenio del paquete si importes son = los borro, sino los dejo 'sin usar'.
        }
    }

    private static void agregarKmsACamioneta(Camioneta camionetaAsociado, int distanciaARecorrer, CamionetaService camionetaService) {
        Long kmActuales = camionetaAsociado.getKmsRecorridos();
        camionetaAsociado.setKmsRecorridos(kmActuales + distanciaARecorrer);
        camionetaService.editarCamioneta(camionetaAsociado);
    }
}
