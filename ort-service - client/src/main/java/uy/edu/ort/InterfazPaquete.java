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
import static uy.edu.ort.MainUserService.ListarClientes;
import static uy.edu.ort.MainUserService.esFecha;
import static uy.edu.ort.MainUserService.esPositivo;
import static uy.edu.ort.MainUserService.esUnCliente;
import static uy.edu.ort.MainUserService.validarOpcion;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;
import uy.edu.ort.model.Paquete;
import uy.edu.ort.service.ClienteService;
import uy.edu.ort.service.ConvenioService;
import uy.edu.ort.service.PaqueteService;

/**
 *
 * @author Guillermo
 */
public final class InterfazPaquete {

    public static void PaqueteInferfaz(ApplicationContext applicationContext) {

        PaqueteService paqueteService = (PaqueteService) applicationContext.getBean("paqueteService");
        ClienteService clienteService = (ClienteService) applicationContext.getBean("clienteService");
        //TODO VER SI BORRAR EL CLIENTESERVICE
        ConvenioService convenioService = (ConvenioService) applicationContext.getBean("convenioService");

        String[] menu = {"Ingresar Paquete", "Editar Paquete", "Eliminar Paquete", "Listar Paquetes", "Volver"};
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        while (opcion != menu.length) {
            //muestra menu
            System.out.println("Menu Paquete");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }
            opcion = validarOpcion(menu.length);
            switch (opcion) {
                case 1: {
                    Paquete paquete = new Paquete();
                    System.out.println("Código: ");
                    paquete.setCodigo(in.nextLine());
                    System.out.println("Fecha de creación (ej. 31-12-2016): ");
                    paquete.setFechaCreacion(esFecha(in.nextLine()));

                    System.out.println("Costo: ");
                    int costoDePaquete = (int) esPositivo(in);
                    paquete.setCosto(costoDePaquete);

                    System.out.println("Nombre de la empresa de destino (se muestra un listado con las opciones): ");
                    List<Cliente> listadoClientes = clienteService.listCliente();
                    ListarClientes(listadoClientes);
                    String nombreCliente = in.nextLine();
                    boolean esCliente = esUnCliente(nombreCliente, listadoClientes);
                    boolean clienteFueAgregado = false;
                    int descuento = 0;
                    if (esCliente) {
                        Cliente clienteAsociado = clienteService.buscarClientePorNombreEmpresa(nombreCliente);
                        paquete.setCliente(clienteAsociado);

                        //logica para descuentos con convenio
                        //TODO asignar lista de convenios bien.
                        //List<Convenio> convenios =  new ArrayList<Convenio>(); 
                        //clienteAsociado.getListaConvenios();
                        List<Convenio> convenios = convenioService.listConvenio();
                        if (existeConvenioLibre(convenios, clienteAsociado.getNombreEmpresa())) {
                            Convenio convenioLibre = obtenerPrimerConvenioLibre(convenios, clienteAsociado.getNombreEmpresa());
                            convenioLibre.setEstaEnUso(true);
                            descuento = convenioLibre.getImporteInicialConvenio() - convenioLibre.getImporteActualConvenio();

                            if (costoDePaquete < descuento) {
                                descuento = costoDePaquete;
                            }

                            //TODO ACTUALIZAR CONVENIO
                            paquete.setConvenio(convenioLibre);

                            //REVISAR ESTO
                            //TODO no actualia bien el importeactual de convenio.. algo raro
                            
                            int impActual = convenioLibre.getImporteActualConvenio();
                            convenioLibre.setImporteActualConvenio(costoDePaquete + impActual);
                            convenioService.editarConvenio(convenioLibre);
                        }
                    }
                    System.out.println("Descuento obtenido por convenio (no estoy teniendo en cuenta lo del 20%): " + descuento);
                    paquete.setDescuento(descuento);
                    System.out.println("Peso: ");
                    paquete.setPeso((int) esPositivo(in));
                    System.out.println("Descripción: ");
                    paquete.setDescripcion(in.nextLine());
                    paqueteService.addPaquete(paquete);
                    break;
                }

                case 2: {
                    System.out.println("Codigo: ");
                    Paquete paquete = paqueteService.buscarPaquete(in.nextLine());
                    if (paquete == null) {
                        System.out.println("No existe un paquete con ese codigo.");
                    } else {
                        System.out.println("datos de Paquete a modificar: ");
                        System.out.println("Código: " + paquete.getCodigo());
                        System.out.println("Fecha de creación: " + paquete.getFechaCreacion().toString());
                        System.out.println("Nombre de la empresa: " + paquete.getCliente().getNombreEmpresa());
                        System.out.println("Costo: " + paquete.getCosto());
                        System.out.println("Peso: " + paquete.getPeso());
                        System.out.println("Descripción: " + paquete.getDescripcion());
                        System.out.println("Nombre de la empresa de destino: " + paquete.getCliente().getNombreEmpresa());

                        System.out.println("");
                        System.out.println("Modificación de datos: ");
                        System.out.println("Codigo: ");
                        paquete.setCodigo(in.nextLine());
                        System.out.println("Fecha de creación (ej. 31-12-2016): ");
                        paquete.setFechaCreacion(esFecha(in.nextLine()));

                        System.out.println("Costo: ");
                        paquete.setCosto((int) esPositivo(in));
                        System.out.println("Peso: ");
                        paquete.setPeso((int) esPositivo(in));
                        System.out.println("Descripción: ");
                        paquete.setDescripcion(in.nextLine());
                        paqueteService.removePaquete(paquete);
                        paqueteService.addPaquete(paquete);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Codigo: ");
                    Paquete paquete = paqueteService.buscarPaquete(in.nextLine());
                    if (paquete == null) {
                        System.out.println("No existe un paquete con ese codigo.");
                    } else {
                        paqueteService.removePaquete(paquete);
                        System.out.println("El paquete ha sido eliminada con exito.");
                    }
                    break;
                }
                case 4: {
                    ListarPaquetes(paqueteService.listPaquetes());
                    break;
                }

                default: {
                }

            }
        }
    }

    public static void ListarPaquetes(List<Paquete> listPaquete) {
        for (Paquete p : listPaquete) {
            System.out.println("");
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Fecha de creación: " + p.getFechaCreacion());
            System.out.println("Nombre de la empresa: " + p.getCliente().getNombreEmpresa());
            System.out.println("Costo: " + p.getCosto());
            System.out.println("Peso: " + p.getPeso());
            System.out.println("Descripción: " + p.getDescripcion());
        }
    }

    public static boolean hayConvenioLibre(List<Convenio> convenios) {
        boolean hayConvenio = false;
        for (Convenio c : convenios) {
            if (!c.isEstaEnUso()) {
                hayConvenio = true;
            }
        }
        return hayConvenio;
    }

    public static Convenio obtenerPrimerConvenioLibre(List<Convenio> convenios, String nombreEmpresaASociada) {
        Convenio convenioDisponible = new Convenio();
        boolean convenioEncontrado = false;
        for (Convenio c : convenios) {
            if (!c.isEstaEnUso() && !convenioEncontrado && c.getCliente().getNombreEmpresa().equals(nombreEmpresaASociada)) {
                convenioEncontrado = true;
                convenioDisponible = c;
            }
        }
        return convenioDisponible;
    }

    public static boolean existeConvenioLibre(List<Convenio> convenios, String nombreEmpresaASociada) {
        //TODO FALLA SI NO TIENE CLIENTE ASOCIADO
        boolean convenioEncontrado = false;
        for (Convenio c : convenios) {
            if (!c.isEstaEnUso() && !convenioEncontrado && c.getCliente().getNombreEmpresa().equals(nombreEmpresaASociada)) {
                convenioEncontrado = true;
            }
        }
        return convenioEncontrado;
    }
}
