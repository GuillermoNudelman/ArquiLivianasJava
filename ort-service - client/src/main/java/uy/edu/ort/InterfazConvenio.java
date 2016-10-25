/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort;

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
import uy.edu.ort.service.ConvenioService;

import uy.edu.ort.service.ClienteService;

/**
 *
 * @author Guillermo
 */
public final class InterfazConvenio {

    public static void ConvenioInferfaz(ApplicationContext applicationContext) {

        ConvenioService convenioService = (ConvenioService) applicationContext.getBean("convenioService");
        ClienteService clienteService = (ClienteService) applicationContext.getBean("clienteService");

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
                    //validar clientes
                    List<Cliente> listadoClientes = clienteService.listCliente();
                    if (!listadoClientes.isEmpty()) {
                        System.out.println("Recuerde que el 'importe actual' del convenio se inicializa en 0, y va aumentando a medida que se hace uso del convenio. Cuando se alcanza el 'monto inicial', el convenio se elimina.");
                        Convenio convenio = new Convenio();

                        String codigoIngresado = "";
                        boolean codigoLibre = false;
                        System.out.println("Codigo: ");
                        while (!codigoLibre) {
                            codigoIngresado = in.nextLine();
                            codigoLibre = (convenioService.buscarConvenio(codigoIngresado) == null);
                            if (!codigoLibre) {
                                System.out.println("Codigo en uso. Reingrese: ");
                            }
                        }
                        convenio.setCodigo(codigoIngresado);

                        System.out.println("Fecha de creación (ej. 31-12-2016): ");
                        convenio.setFechaCreacion(esFecha(in.nextLine()));
                        System.out.println("Nombre de la empresa (se muestra un listado con las opciones): ");
                        boolean esCliente = false;
                        while (!esCliente) {
                            listadoClientes = clienteService.listCliente();
                            ListarClientes(listadoClientes);
                            String nombreCliente = in.nextLine();
                            esCliente = esUnCliente(nombreCliente, listadoClientes);
                            boolean clienteFueAgregado = false;
                            if (esCliente) {
                                Cliente clienteAsociado = clienteService.buscarClientePorNombreEmpresa(nombreCliente);
                                convenio.setCliente(clienteAsociado);
                            } else {
                                System.out.println("Nombre de la empresa incorrecto. Reingrese (se muestra un listado con las opciones): ");
                            }
                        }
                        System.out.println("Importe Inicial: ");
                        convenio.setImporteInicialConvenio((int) esPositivo(in));

                        convenio.setImporteActualConvenio(0);
                        /*
                    System.out.println("Importe Actual: ");
                    convenio.setImporteActualConvenio((int) esPositivo(in));
                         */
                        convenioService.addConvenio(convenio);

                    } else {
                        System.out.println("No es posible ingresar convenios ya que aun no existen clientes a quien asignarselos.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Codigo: ");
                    Convenio convenio = convenioService.buscarConvenio(in.nextLine());
                    if (convenio == null) {
                        System.out.println("No existe un convenio con ese codigo.");
                    } else {
                        System.out.println("datos de Convenio a modificar: ");
                        System.out.println("Código: " + convenio.getCodigo());
                        System.out.println("Fecha de creación: " + convenio.getFechaCreacion().toString());

                        //TODO NOMBRE D EEMPRESA
                        System.out.println("VOLVER A ELEGIR NOMBRE DE EMPRESA");
                        System.out.println("Nombre de la empresa: " + convenio.getCliente().getNombreEmpresa());
                        System.out.println("Importe inicial: " + convenio.getImporteInicialConvenio());
                        //System.out.println("Importe actual: " + convenio.getImporteActualConvenio());
                        System.out.println("");

                        System.out.println("Modificación de datos: ");

                        String codigoIngresado = "";
                        boolean codigoLibre = false;
                        System.out.println("Codigo: ");
                        while (!codigoLibre) {
                            codigoIngresado = in.nextLine();
                            codigoLibre = (convenioService.buscarConvenio(codigoIngresado) == null);
                            if (!codigoLibre) {
                                System.out.println("Codigo en uso. Reingrese: ");
                            }
                        }
                        convenio.setCodigo(codigoIngresado);

                        System.out.println("Fecha de creación (ej. 31-12-2016): ");
                        convenio.setFechaCreacion(esFecha(in.nextLine()));

                        System.out.println("Nombre de la empresa (se muestra un listado con las opciones): ");
                        List<Cliente> listadoClientes = clienteService.listCliente();
                        ListarClientes(listadoClientes);
                        String nombreCliente = in.nextLine();
                        boolean esCliente = esUnCliente(nombreCliente, listadoClientes);
                        if (esCliente) {
                            Cliente clienteAsociado = clienteService.buscarClientePorNombreEmpresa(nombreCliente);
                            // convenio.setCliente(clienteAsociado);
                        }

                        System.out.println("Importe Inicial: ");
                        convenio.setImporteInicialConvenio((int) esPositivo(in));
                        System.out.println("Importe Actual: ");
                        convenio.setImporteActualConvenio((int) esPositivo(in));

                        //todo chequear estoo de abajo!!
                        convenioService.removeConvenio(convenio);
                        convenioService.addConvenio(convenio);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Codigo: ");
                    Convenio convenio = convenioService.buscarConvenio(in.nextLine());
                    if (convenio == null) {
                        System.out.println("No existe un convenio con ese codigo.");
                    } else {
                        convenioService.removeConvenio(convenio);
                        System.out.println("El convenio ha sido eliminada con exito.");
                    }
                    break;
                }
                case 4: {
                    List<Convenio> convenios = convenioService.listConvenio();
                    for (Convenio c : convenios) {
                        System.out.println("Código de convenio: " + c.getCodigo());
                        //       System.out.println("    Nombre de empresa del cliente: " + c.getCliente().getNombreEmpresa());
                        System.out.println("    Importe inicial: " + c.getImporteInicialConvenio());
                        System.out.println("    Importe inicial: " + c.getImporteInicialConvenio());
                        System.out.println("    Importe actual: " + c.getImporteActualConvenio());
                        System.out.println("");
                    }
                    break;
                }
                default: {
                }

            }
        }
    }

}
